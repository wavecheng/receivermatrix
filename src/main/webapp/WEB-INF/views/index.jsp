<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Citrix Receiver Feature Matrix</title>
	<link rel="stylesheet" media="screen" href="${contextPath}/resources/handsontable.full.min.css">
	<link rel="stylesheet" media="screen" href="${contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
    	<h2>Receiver Feature Matrix</h2>   	
      <div class="row">
      	 <div class="msgConsole"></div>
         <div id="example1"></div>
        <p>
          <button name="save" id="save">
            Save to DB
          </button>
        </p>
  	  </div>

    </div>
</body>
<script src="${contextPath}/resources/jquery.min.js"></script>
<script src="${contextPath}/resources/bootstrap.min.js"></script>
<script src="${contextPath}/resources/handsontable.full.min.js"></script>
<script>
var container = document.getElementById('example1');

/* 	var hot1 = new Handsontable(container, {
		data: Handsontable.helper.createSpreadsheetData(1, 2),
		rowHeaders: true,
		contextMenu: true,
		mergeCells: [
		],
		className: "htCenter",
		cell: [
		  {row: 0, col: 0, className: "htRight"},
		],
		afterSetCellMeta: function (row, col, key, val) {
		  console.log("cell meta changed", row, col, key, val);
		}
	}); */
	
	var autosaveNotification;
	var hotOptions = {
		  startRows: 2,
		  startCols: 2,
		  rowHeaders: true,
		  minSpareRows: 1,
		  manualColumnResize: true,
		  contextMenu: true,
		  className: "htMiddle htCenter",
		  mergeCells:[],
		  /*
		  afterChange: function (change, source) {			  
		    if (source === 'loadData') {
		      return; //don't save this change
		    }
		    clearTimeout(autosaveNotification);
		    var json = {
		    	row:change[0][0],
		    	column:change[0][1],
		    	value:change[0][3]
		    }		    
		    var request = $.ajax({url:'save',method:'POST',data:JSON.stringify(json),contentType:"application/json"});
		    request.success(function(data){
		      $(".msgConsole").html('Autosaved (' + change.length + ' ' + 'cell' + (change.length > 1 ? 's' : '') + ')');
		      autosaveNotification = setTimeout(function() {
		    	  $(".msgConsole").html('Changes will be autosaved');
		      }, 1000);
		    }); 
		  },
		  afterGetCellMeta: function (row, col, key, val) {
 		    console.log(this.mergeCells);
    		console.log("cell meta changed", row, col, key, val);
    	 }
		    */
	};
	
	var hot = new Handsontable(container, hotOptions);
	
	$("#save").click(function(){
		clearTimeout(autosaveNotification);
	    var request = $.ajax({url:'save',method:'POST',data:JSON.stringify(hot.getData()),contentType:"application/json"});
	    request.success(function(data){
	      $(".msgConsole").html('save successfully');
	      autosaveNotification = setTimeout(function() {
	    	  $(".msgConsole").html('');
	      }, 1000);
	    });
	    
	    var request = $.ajax({url:'save_config',method:'POST',data:JSON.stringify(hot.mergeCells.mergedCellInfoCollection),contentType:"application/json"});
	    request.success(function(data){
	      console.log("save config success");
	    }); 
	});
	
	
	$(function(){
		var request = $.ajax({url:'load'});
		request.success(function(data){
			hot.loadData(JSON.parse(data.tableData));		
			hot.render();
		});
		
		request = $.ajax({url:'load_config'});
		request.success(function(data){
			hotOptions.mergeCells = JSON.parse(data.tableData);
			hot.mergeCells = new Handsontable.MergeCells(hotOptions.mergeCells);
			hot.updateSettings(hotOptions);		
		});
	});
</script>
</html>
