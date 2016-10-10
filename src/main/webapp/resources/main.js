	var container = document.getElementById('example1');
	var changed = false;
	var hotOptions = {
		  startRows: 2,
		  startCols: 2,
		  rowHeaders: true,
		  minSpareRows: 1,
		  manualColumnResize: true,
		  contextMenu: true,
		  className: "htMiddle htCenter",
		  mergeCells:[],
		  afterChange: function (change, source) {			  
		    if (source === 'loadData') {
		      return; //don't save this change
		    }
			changed = true;
			$("#save").show();
		  },
	};
	
	var hot = new Handsontable(container, hotOptions);
	
	$("#save").click(function(){
	    var request = $.ajax({url:'save',method:'POST',data:JSON.stringify(hot.getData()),contentType:"application/json"});
	    request.success(function(data){
	      $(".msgConsole").html('save successfully!');
	      setTimeout(function() {
	    	  $(".msgConsole").html('');
	    	  $("#save").hide();
	      }, 1500);
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
		$("#save").hide();
	});