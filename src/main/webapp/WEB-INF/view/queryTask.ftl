<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../../../js/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="../../../js/themes/icon.css">
		<link rel="stylesheet" media="screen" type="text/css" href="../../../js/demo/demo.css">		
      
        <script type="text/javascript" src="../../../js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="../../../js/jquery.easyui.min.js"></script>
		
	</head>
	
	<body style="padding:2px; margin:2px;">
       
        
        <div style="margin:0px 0;"></div>
        
	    <!--align:'right' -->
		<table id="log" class="easyui-datagrid" title="数据上传任务日志" style="width:1200px"
				data-options="rownumbers:true,
				              singleSelect:true,				              
				              pagination:true,
				              url:'../queryData/${appName!"null"}/${userName!"null"}',
				              pageSize:20,
				              fit: true,   //自适应大小
				              toolbar:'#tb'">
			<thead>
				<tr>
					<th data-options="field:'job_id',width:50">作业ID</th>
					<th data-options="field:'year',width:40">年度</th>
					<th data-options="field:'period',width:40">月份</th>
					<th data-options="field:'entity',width:100,align:'center'">组织</th>
					<th data-options="field:'scenario',width:80">情景</th>
					<th data-options="field:'params',width:80,formatter:function(value,row,index){
					if(value==1){
					  return '合并'
					} else {
					  return '替换'
					}
					}">加载模式</th>
					<th data-options="field:'map_set_name',width:180">上传报表</th>					
					<th data-options="field:'start_time',width:140">作业启动时间</th>
					<th data-options="field:'end_time',width:140">作业完成时间</th>	
					<th data-options="field:'user',width:60">用户</th>				
					<th data-options="field:'status',width:60,formatter:function(value,row,index){
	             if(value==0){
	                    return '等待执行'}  
	             else if(value == 1){
	                     return '正在执行'
	             } else if(value == 2){
	                     return '上传成功'
	             }
                 else {
                        return '上传失败'}                        
                  }">状态</th>
					<th data-options="field:'message',width:480">信息</th>
				</tr>
			</thead>
		</table>
	
	 

    
	<div id="tb" style="padding:5px;height:auto">
		

		    <!--
			Date From: <input class="easyui-datebox" style="width:80px">
			To: <input class="easyui-datebox" style="width:80px">
			-->
			 
			<input type="hidden" name="userName" id="userName" value="${userName!"null"}" />
	        <input type="hidden" name="appName" id="appName" value="${appName!"null"}" />
	        
			<label for='queryTable' class='l-btn-text' >上传报表:</label>	
			<select class="easyui-combobox" panelHeight="auto" style="width:100px" id="queryTable" 
			   data-options="panelWidth: 400,
			                        valueField: 'schemeId',
									textField: 'schemeName',
									url: '../../interfaceTables'">
			</select>
			
			<label for='queryEntity' class='l-btn-text' >上传组织:</label>
			<input id='queryEntity' style='display:inline-block;width:100px' class='easyui-textbox' />
			
			<label for='queryYear' class='l-btn-text' >上传年度:</label>
			<select class="easyui-combobox" style="width:200px" id="queryYear">
			                     <option value="2014">2014</option>
                                 <option value="2015">2015</option>
                                 <option value="2016">2016</option>
                                 <option value="2017">2017</option>
                                 <option value="2018">2018</option>
                                 <option value="2019">2019</option>
                                 <option value="2020">2020</option>
                                 <option value="2021">2021</option>
                                 <option value="2022">2022</option>
                                 <option value="2023">2023</option>
                                 <option value="2024">2024</option>
                        </select>
			
			<label for='queryMonth' class='l-btn-text' >上传月份:</label>
			<select class="easyui-combobox" style="width:200px" id="queryMonth">
			                     <option value="" selected>全部</option>
                        		 <option value="Jan">1月</option>
                                 <option value="Feb">2月</option>
                                 <option value="Mar">3月</option>
                                 <option value="Apr">4月</option>
                                 <option value="May">5月</option>
                                 <option value="Jun">6月</option>
                                 <option value="Jul">7月</option>
                                 <option value="Aug">8月</option>
                                 <option value="Sep">9月</option>
                                 <option value="Oct">10月</option>
                                 <option value="Nov">11月</option>
                                 <option value="Dec">12月</option>
                        </select>
			
			<a href="javascript:void(0)" onclick="queryData()" class="easyui-linkbutton" iconCls="icon-search">Search</a>

	</div>
	
	<script type="text/javascript">
	       
	        
	        function queryData(){

					var queryParams = $('#log').datagrid('options').queryParams;  
					
					queryParams.queryTable = $("#queryTable").combobox('getValue');
					queryParams.queryEntity = $("#queryEntity").val();
					queryParams.queryYear = $("#queryYear").combobox('getValue');  
					queryParams.queryMonth =$("#queryMonth").combobox('getValue');  
					
					$("#log").datagrid('reload'); 
				}
	       
	       $(document).ready(function(){
	       
	          //var bodyh = $(document.body).height();//document.body.clientHeight;
	          //var v1h   = $("#tb").outerHeight();
              //$("#log").height(bodyh - 20);
    
			　　 var mydate = new Date();
			   var months=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
			    
               var str = "" + mydate.getFullYear();
			   $('#queryYear').combobox('setValue',str);
			   
			   var month=mydate.getMonth();
 			   //$('#queryMonth').combobox('setValue',months[month]);
 			   //$('#queryMonth').combobox('setValue','');
			});
			
	</script>
	</body>
</html>

