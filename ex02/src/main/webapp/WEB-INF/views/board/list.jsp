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
 						<!-- 	반환 된 prevt가 참이면 아래 코드를 출력  -->
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
	});

</script>
            
      
            <%@include file="../includes/footer.jsp"%>
