<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							Board List Page
							<button id='regBtn' type="button" class="btn btn-xs pull-right">Register
								New Board</button>
						</div>

                        <!-- /.panel-heading -->
                        <div class="panel-body">				
                        <table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>

					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.bno}" /></td>
							<%-- <td><a href='/board/get?bno=<c:out value="${board.bno}"/>'>
							<c:out value="${board.title}"/></a></td> --%>

							<td><a class='move' href='<c:out value="${board.bno}"/>'>
									<c:out value="${board.title}" />
							</a></td>

							<td><c:out value="${board.writer}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.regdate}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.updateDate}" /></td>
						</tr>
					</c:forEach>
				</table>
			 <h4>${pageMaker}</h4> 
			 
		 	<div class='row'>
				<div class="col-lg-12">
					<form id='searchForm' action="/board/list" method='get'>
						<select name='type'>
							<option value=""
								<c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
							<option value="T"
								<c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
							<option value="C"
								<c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
							<option value="W"
								<c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
							<option value="TC"
								<c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목 or 내용</option>
							<option value="TW"
								<c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목 or 작성자</option>
							<option value="TWC"
								<c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>> 제목 or 내용 or 작성자</option>
						</select> 
						
						<input type='text' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>'/>
						<input type='hidden' name='pageNum'value='<c:out value="${pageMaker.cri.pageNum}"/>'/>
						<input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount}"/>'/>
						<button class='btn btn-default'>Search</button>
					</form>
				</div>
			</div>
			
			
			<div class='pull-right'>
					<ul class="pagination">
<%-- 
	        <c:if test="${pageMaker.prev}">
              <li class="paginate_button previous"><a href="#">Previous</a>
              </li>
            </c:if>

            <c:forEach var="num" begin="${pageMaker.startPage}"
              end="${pageMaker.endPage}">
              <li class="paginate_button"><a href="#">${num}</a></li>
            </c:forEach>

            <c:if test="${pageMaker.next}">
              <li class="paginate_button next"><a href="#">Next</a></li>
            </c:if> 
 --%>
 						<!-- 	반환 된 prev가 참이면 아래 코드를 출력  -->
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage -1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>
							
				<!-- 		반환 된 next가 참이면 아래 코드를출력  -->
						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage +1 }">Next</a></li>
						</c:if>
 

					</ul>
				</div>
				
				<form id='actionForm' action="/board/list" method='get'>
					<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
					<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
					
					<!-- 페이지 번호 클릭 후 이동할 때에도 검색조건과 키워드는 같이 전달 -->
					<input type='hidden' name='type' value='<c:out value="${ pageMaker.cri.type }"/>'>
					<input type='hidden' name='keyword'value='<c:out value="${ pageMaker.cri.keyword }"/>'>
				</form>
				
                 <!-- /.table-responsive -->

             </div>
             <!-- /.panel-body -->
         </div>
         <!-- /.panel -->
     </div>
     <!-- /.col-lg-12 -->
 </div>
            

            
            
<script type="text/javascript">
$(document).ready(function() {

	var result = '<c:out value="${result}"/>';

	checkModal(result);
	
	// window.history 속 데이터 지우기 (모달창, 다운로드 등의 쌓임 방지)
	 history.replaceState({}, null, null);
	
	// 체크모달 함수
	function checkModal(result) {
		
		// 만약 공백이면 그냥 종료
		if (result === '' || history.state) {
			return;
		}
	
		// result를 숫자로 변형 후 0보다 크면
		if (parseInt(result) > 0) {
			// 결과 보여주기
			$(".modal-body").html( "게시글 " + parseInt(result)
							+ " 번이 등록되었습니다.");
		}
		// div 속 id와 일치한 모달에 보여주기
		$("#myModal").modal("show");
	}
		// Register New Board 버튼 클릭시 동작 함수
		$("#regBtn").on("click", function() {
		
		// 현재 윈도우(self)에 "/board/register"로 이동해
		self.location = "/board/register";

		});
		
		// <a> 태그가 원래의 동작을 못하도록 JavaScrpit 처리
		var actionForm = $("#actionForm");
	
		$(".paginate_button a").on("click",function(e) {
		
			e.preventDefault();
		
			console.log('click');
		
			// actionForm안에 찾는다 input[name='pageNum']를 그 값을 정한다. $(this).attr("href")로
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
			});
		
		// 게시물 제목 링크 클릭 후 조회 결과 페이지로 이동하도록 이벤트 처리.
		$(".move").on("click",function(e) {
			e.preventDefault();
			
			//  actionForm안에 내용을 대체한다
			actionForm.append("<input type='hidden' name='bno' value='"
			+ $(this).attr("href") + "'>");
			
			// actionForm 액션 값 수정 
			actionForm.attr("action", "/board/get");
			actionForm.submit();

		});
		
		//  검색 버튼을 클릭하면 검색은 1페이지를 하도록 수정, 검색 조건과 키워드가 보이게 처리
		var searchForm = $("#searchForm");

		$("#searchForm button").on("click", function(e) {
			
			// 검색 조건과 키워드가 보이게 처리
			if (!searchForm.find("option:selected") .val()) {
				alert("검색종류를 선택하세요");
				return false;
			}
	
			if (!searchForm.find("input[name='keyword']").val()) {
				alert("키워드를 입력하세요");
				return false;
			}
	
			// 검색 버튼을 클릭하면 검색은 1페이지를 하도록 수정
			searchForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
	
			searchForm.submit();
		});

	});
	
	

</script>
            
      
            <%@include file="../includes/footer.jsp"%>
