 <%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Read</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Board Read Page</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

          <div class="form-group">
          <label>Bno</label> <input class="form-control" name='bno'
            value='<c:out value="${board.bno }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>Title</label> <input class="form-control" name='title'
            value='<c:out value="${board.title }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>Text area</label>
          <textarea class="form-control" rows="3" name='content'
            readonly="readonly"><c:out value="${board.content}" /></textarea>
        </div>

        <div class="form-group">
          <label>Writer</label> <input class="form-control" name='writer'
            value='<c:out value="${board.writer }"/>' readonly="readonly">
        </div>

    
    <button data-oper='list' class="btn btn-info"> <a href="/board/list">List</a></button> 
    <button data-oper='modify' class="btn btn-default"> <a href="/board/modify?bno=<c:out value="${board.bno}"/>">Modify</a></button>


<%-- <form id='operForm' action="/boad/modify" method="get">
  <input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
</form> --%>

        
		<!-- // 버튼 클릭시 정상적으로 목록페이지로 이동 -->
		<form id='operForm' action="/boad/modify" method="get">
		  <input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
		  <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
		  <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'> 
		  
		 <!-- Criteria의 type과 keyword에 대한 처리 -->
		  <input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
  		  <input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
		</form>


<!-- < /board/modify?bno=xx 이동, /board/list 이동 버튼을 추가. 
<button data-oper='modify' class="btn btn-default">Modify</button>
<button data-oper='list' class="btn btn-info">List</button> -->

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<div class='row'>

  <div class="col-lg-12">

    <!-- /.panel -->
    <div class="panel panel-default">
<!--       <div class="panel-heading">
        <i class="fa fa-comments fa-fw"></i> Reply
      </div> -->
      
      <div class="panel-heading">
        <i class="fa fa-comments fa-fw"></i> Reply
        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
      </div>      
      
      
      <!-- /.panel-heading -->
      <div class="panel-body">        
      
        <ul class="chat">

        </ul>
        <!-- ./ end ul -->
      </div>
      <!-- /.panel .chat-panel -->

	<div class="panel-footer"></div>


		</div>
  </div>
  <!-- ./ end row -->
</div>




<!-- Modal -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">&times;</button>
              <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label>Reply</label> 
                <input class="form-control" name='reply' value='New Reply!!!!'>
              </div>      
              <div class="form-group">
                <label>Replyer</label> 
                <input class="form-control" name='replyer' value='replyer'>
              </div>
              <div class="form-group">
                <label>Reply Date</label> 
                <input class="form-control" name='replyDate' value='2018-01-01 13:13'>
              </div>
      
            </div>
<div class="modal-footer">
        <button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
        <button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
        <button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
        <button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
      </div>          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
      <!-- /.modal -->



<script type="text/javascript" src="/resources/js/reply.js"> </script>



<script>
//게시글의 조회 페이지가 열리면 자동으로 댓글 목록을 가져와서 <li> 태그를 구성
//이에 대한 처리는 $(document).ready()내에서 이루어 진다.
$(document).ready(function () {

var bnoValue = '<c:out value="${board.bno}"/>';
var replyUL = $(".chat");

 showList(1);
 // show.List()는 페이지 번호를 파라미터로 받도록 설계
 function showList(page){
 	
 	console.log("show list " + page);
     
 	// 파라미터가 없다면 자동으로 1페이지로 설정
 	// 만일 1페이지가 아닌 경우라면 기존 <ul>에 <li>들이 추가되는 형태
 	 replyService.getList({bno:bnoValue,page: page|| 1 }, function(replyCnt, list) {
         
         console.log("replyCnt: "+ replyCnt );
         console.log("list: " + list);
         console.log(list);
         
         if(page == -1){
           pageNum = Math.ceil(replyCnt/10.0);
           showList(pageNum);
           return;
         }
           
          var str="";
          
          if(list == null || list.length == 0){
            return;
          }
          
          for (var i = 0, len = list.length || 0; i < len; i++) {
            str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
            str +="  <div><div class='header'><strong class='primary-font'>["
         	   +list[i].rno+"] "+list[i].replyer+"</strong>"; 
            str +="    <small class='pull-right text-muted'>"
                +replyService.displayTime(list[i].replyDate)+"</small></div>";
            str +="    <p>"+list[i].reply+"</p></div></li>";
          }
          
          replyUL.html(str);
          
          showReplyPage(replyCnt);

      
        });//end function
          
      }//end showList
 
	 var pageNum = 1;
	 var replyPageFooter = $(".panel-footer");
	 
	 function showReplyPage(replyCnt){
	   
	   var endNum = Math.ceil(pageNum / 10.0) * 10;  
	   var startNum = endNum - 9; 
	   
	   var prev = startNum != 1;
	   var next = false;
	   
	   if(endNum * 10 >= replyCnt){
	     endNum = Math.ceil(replyCnt/10.0);
	   }
	   
	   if(endNum * 10 < replyCnt){
	     next = true;
	   }
	   
	   var str = "<ul class='pagination pull-right'>";
	   
	   if(prev){
	     str+= "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
	   }
	   
	    
	   
	   for(var i = startNum ; i <= endNum; i++){
	     
	     var active = pageNum == i? "active":"";
	     
	     str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
	   }
	   
	   if(next){
	     str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
	   }
	   
	   str += "</ul></div>";
	   
	   console.log(str);
	   
	   replyPageFooter.html(str);
	 }
  
	 
	 /* 마지막 처리는 페이지의 번호를 클릭했을 때 새로운 댓글을 가져오도록 하는 부분 */
     
    replyPageFooter.on("click","li a", function(e){
        e.preventDefault();
        console.log("page click");
        
        var targetPageNum = $(this).attr("href");
        
        console.log("targetPageNum: " + targetPageNum);
        
        pageNum = targetPageNum;
        
        showList(pageNum);
      });     

    
 
	 var modal = $(".modal");
	 var modalInputReply = modal.find("input[name='reply']");
	 var modalInputReplyer = modal.find("input[name='replyer']");
	 var modalInputReplyDate = modal.find("input[name='replyDate']");
	 
	 var modalModBtn = $("#modalModBtn");
	 var modalRemoveBtn = $("#modalRemoveBtn");
	 var modalRegisterBtn = $("#modalRegisterBtn");
	 
	 $("#modalCloseBtn").on("click", function(e){
	 	
	 	modal.modal('hide');
	 });
	 
	 $("#addReplyBtn").on("click", function(e){
	   
	   modal.find("input").val("");
	   modalInputReplyDate.closest("div").hide();
	   modal.find("button[id !='modalCloseBtn']").hide();
	   
	   modalRegisterBtn.show();
	   
	   $(".modal").modal("show");
	   
	 });
	 
	 
	
	 modalRegisterBtn.on("click",function(e){
	   
	   var reply = {
	         reply: modalInputReply.val(),
	         replyer:modalInputReplyer.val(),
	         bno:bnoValue
	       };
	   replyService.add(reply, function(result){
	     
	     alert(result);
	     
	     modal.find("input").val("");
	     modal.modal("hide");
	     
	     //showList(1);
	     showList(-1);
	     
	   });
	   
	 });
 
 //댓글 조회 클릭 이벤트 처리 
 // <ul>태그의 클래스 ‘chat’을 이용해서 이벤트를 걸고 실제 이벤트의 대상은 <li> 태그
 $(".chat").on("click", "li", function(e){
   
     var rno = $(this).data("rno");
     
     replyService.get(rno, function(reply){
     
       modalInputReply.val(reply.reply);
       modalInputReplyer.val(reply.replyer);
       modalInputReplyDate.val(replyService.displayTime( reply.replyDate))
       .attr("readonly","readonly");
       modal.data("rno", reply.rno);
       
       modal.find("button[id !='modalCloseBtn']").hide();
       modalModBtn.show();
       modalRemoveBtn.show();
       
       $(".modal").modal("show");
           
   	  });
	});
     
     modalModBtn.on("click", function(e){
   	  
      	  var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
      	  
      	  replyService.update(reply, function(result){
      	        
      	    alert(result);
      	    modal.modal("hide");
      	    showList(pageNum);
      	    
      	  });
      	  
      	});


      	modalRemoveBtn.on("click", function (e){
      	  
      	  var rno = modal.data("rno");
      	  
      	  replyService.remove(rno, function(result){
      	        
      	      alert(result);
      	      modal.modal("hide");
      	      showList(pageNum);
      	      
      	  });
      	  
      	});
      
 });
/* 	console.log("==========");
	console.log("JS Test");
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	
	// Ajax 호출은 replyService라는 이름의 객체에 감쳐줘 있어 필요한 파라미터들만 전달하는 형태
	// for replyService add test
	replyService.add (
		{reply:"JS TEST", replyer : "tester", bno:bnoValue},
		function(result){
			alert("Result : " + result);
		}
	); */
</script>


<script type="text/javascript">

//reply List Test
// 해당 게시물의 모든 댓글을 가져오는지 확인하는 코드
replyService.getList({bno:bnoValue, page:1}, function(list){
    
	  for(var i = 0,  len = list.length||0; i < len; i++ ){
	    console.log(list[i]);
	  }
});

/* // 12번 댓글 삭제 테스트 
replyService.remove(12, function(count) {

	  console.log(count);
	
	  if (count === "success") {
	    alert("REMOVED");
	  }
	},
	function(err) {
	  alert('ERROR...');
});


//11번 댓글 수정 
replyService.update({
	rno : 15,
	bno : bnoValue,
	reply : "Modified Reply...."
	}, 
	function(result) {
		alert("수정 완료...");

});   */

// get.jsp에서는 단순히 댓글의 번호만을 전달
/* replyService.get(10, function(data) {
	console.log(data);
}); */
</script>

<script type="text/javascript">
$(document).ready(function() {
  
  var operForm = $("#operForm"); 
  
  $("button[data-oper='modify']").on("click", function(e){
    
    operForm.attr("action","/board/modify").submit();
    
  });
  
    
  $("button[data-oper='list']").on("click", function(e){
    
    operForm.find("#bno").remove();
    operForm.attr("action","/board/list")
    operForm.submit();
    
  });  
});
</script>


<%@include file="../includes/footer.jsp"%>
