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
	
	<body>
        <div style="margin:10px 0;"></div>
	    <div class="easyui-tabs" data-options="tools:'#tab-tools'">
	    
	    <div title="科目-Mapping" style="padding:10px;">
	    <table cellpadding="1">
	        <tr>
	        <td>
			<form name="file1" method="post"  enctype="multipart/form-data">
			
				<input type="hidden" name="detailType" id="detailType" value="account" />
				<input type="hidden" name="dimType" id="dimType" value="account" />
				
				<table cellpadding="1">
					<tr>
						<td>
						<input type="file" name="file">
						</td>
						<td>&nbsp;</td>	
						<td>
						<input type="submit" value="&nbsp;上 传&nbsp;"/>
						</td>						
					</tr>
				</table>
			</form>
			</td>
			<td>
				<button id="export1" class="ui-button ui-widget ui-corner-all">&nbsp;导 出&nbsp;</button>
			</td>
			</tr>
		</table>
		
		<table id="log1" class="easyui-datagrid" title="" 
					data-options="rownumbers:true,
					              singleSelect:true,				              
					              pagination:true,
					              url:'../accMapping',
					              pageSize:20
					              ">
				<thead>
					<tr>
						<th data-options="field:'fromAccountCode',width:80">ERP科目编码</th>
						<th data-options="field:'fromAccountName',width:200">ERP科目名称</th>
						<th data-options="field:'toAccountCode',width:80">HFM科目编码</th>
						<th data-options="field:'toAccountName',width:260">HFM科目名称</th>
						
						<th data-options="field:'detailCode1',width:100">ICP(ERP)</th>
						<th data-options="field:'detailCode2',width:100">项目(ERP)</th>
						<th data-options="field:'detailCode3',width:100">产品(ERP)</th>
						<th data-options="field:'detailName4',width:100">数据类型(ERP)</th>
						<th data-options="field:'detailName5',width:100">取数方式(ERP)</th>
						<th data-options="field:'detailCode6',width:100">表名(ERP)</th>
						<th data-options="field:'detailName7',width:60">C1(ERP)</th>
						<th data-options="field:'detailCode8',width:60">C2(ERP)</th>
						
						<th data-options="field:'coef',width:60">调整系数</th>
						
						<th data-options="field:'createDate',width:120">更新时间</th>
					</tr>
				</thead>
			</table>
		
		</div>

		<div title="Entity-Mapping" style="padding:10px;">	
		    <table cellpadding="1">
		        <tr>
		        <td>
				<form name="file2" method="post"  enctype="multipart/form-data">				
				
					<input type="hidden" name="detailType" id="detailType" value="Detail_01" />
					<input type="hidden" name="dimType" id="dimType" value="Dim_01" />
					<table cellpadding="1">
						<tr>
							<td>
							<input type="file" name="file">
							</td>
							<td>&nbsp;</td>	
							<td>
							<input type="submit" value="&nbsp;上 传&nbsp;"/>
							</td>						
						</tr>
					</table>
				</form>
				</td>
				<td>
					<button id="export2" class="ui-button ui-widget ui-corner-all">&nbsp;导 出&nbsp;</button>
				</td>
				</tr>
			</table>
			
			<table id="log2" class="easyui-datagrid" title="" 
					data-options="rownumbers:true,
					              singleSelect:true,				              
					              pagination:true,
					              url:'../dimMapping/Detail_01/Dim_01',
					              pageSize:20
					              ">
				<thead>
					<tr>
						<th data-options="field:'detailCode',width:100">ERP编码</th>
						<th data-options="field:'detailName',width:400">ERP名称</th>
						<th data-options="field:'dimCode',width:100">HFM编码</th>
						<th data-options="field:'dimName',width:400">HFM名称</th>
						<th data-options="field:'createDate',width:120">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div title="ICP-Mapping" style="padding:10px;">
		    <table cellpadding="1">
		        <tr>
		        <td>
				<form name="file3" method="post"  enctype="multipart/form-data">				
				
					<input type="hidden" name="detailType" id="detailType" value="Detail_02" />
					<input type="hidden" name="dimType" id="dimType" value="Dim_02" />
					<table cellpadding="1">
						<tr>
							<td>
							<input type="file" name="file">
							</td>
							<td>&nbsp;</td>	
							<td>
							<input type="submit" value="&nbsp;上 传&nbsp;"/>
							</td>						
						</tr>
					</table>
				</form>
				</td>
				<td>
					<button id="export3" class="ui-button ui-widget ui-corner-all">&nbsp;导 出&nbsp;</button>
				</td>
				</tr>
			</table>
			
			<table id="log3" class="easyui-datagrid" title="" 
					data-options="rownumbers:true,
					              singleSelect:true,				              
					              pagination:true,
					              url:'../dimMapping/Detail_02/Dim_02',
					              pageSize:20,
					              toolbar:'#tb'
					              ">
				<thead>
					<tr>
						<th data-options="field:'detailCode',width:100">ERP编码</th>
						<th data-options="field:'detailName',width:400">ERP名称</th>
						<th data-options="field:'dimCode',width:100">HFM编码</th>
						<th data-options="field:'dimName',width:400">HFM名称</th>
						<th data-options="field:'createDate',width:120">更新时间</th>
					</tr>
				</thead>
			</table>
			
			<div id="tb" style="padding:5px;height:auto">
		
			
			<label for='dimMappingType' class='l-btn-text' >维度类型:</label>
			<select class="easyui-combobox" style="width:200px" id="dimMappingType">
			                     <option value="">全部</option>
			                     <option value="Detail_01">组织</option>
                                 <option value="Detail_02">ICP</option>
                                 <option value="Detail_03">产品</option>
                                 <option value="Detail_04">项目</option>
                        </select>
			<a href="javascript:void(0)" onclick="queryData()" class="easyui-linkbutton" iconCls="icon-search">Search</a>

	</div>
		</div>
		
		<div title="产品-Mapping" style="padding:10px;">
		    <table cellpadding="1">
		        <tr>
		        <td>
				<form name="file4" method="post"  enctype="multipart/form-data">				
				
					<input type="hidden" name="detailType" id="detailType" value="Detail_03" />
					<input type="hidden" name="dimType" id="dimType" value="Dim_04" />
					<table cellpadding="1">
						<tr>
							<td>
							<input type="file" name="file">
							</td>
							<td>&nbsp;</td>	
							<td>
							<input type="submit" value="&nbsp;上 传&nbsp;"/>
							</td>						
						</tr>
					</table>
				</form>
				</td>
				<td>
					<button id="export4" class="ui-button ui-widget ui-corner-all">&nbsp;导 出&nbsp;</button>
				</td>
				</tr>
			</table>
			
			<table id="log4" class="easyui-datagrid" title="" 
					data-options="rownumbers:true,
					              singleSelect:true,				              
					              pagination:true,
					              url:'../dimMapping/Detail_03/Dim_04',
					              pageSize:20
					              ">
				<thead>
					<tr>
						<th data-options="field:'detailCode',width:100">ERP编码</th>
						<th data-options="field:'detailName',width:400">ERP名称</th>
						<th data-options="field:'dimCode',width:100">HFM编码</th>
						<th data-options="field:'dimName',width:400">HFM名称</th>
						<th data-options="field:'createDate',width:120">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div title="项目-Mapping" style="padding:10px;">
		    <table cellpadding="1">
		        <tr>
		        <td>
				<form name="file5" method="post"  enctype="multipart/form-data">				
				
					<input type="hidden" name="detailType" id="detailType" value="Detail_04" />
					<input type="hidden" name="dimType" id="dimType" value="Dim_04" />
					<table cellpadding="1">
						<tr>
							<td>
							<input type="file" name="file">
							</td>
							<td>&nbsp;</td>	
							<td>
							<input type="submit" value="&nbsp;上 传&nbsp;"/>
							</td>						
						</tr>
					</table>
				</form>
				</td>
				<td>
					<button id="export5" class="ui-button ui-widget ui-corner-all">&nbsp;导 出&nbsp;</button>
				</td>
				</tr>
			</table>
					
			<table id="log5" class="easyui-datagrid" title="" 
					data-options="rownumbers:true,
					              singleSelect:true,				              
					              pagination:true,
					              url:'../dimMapping/Detail_04/Dim_04',
					              pageSize:20
					              ">
				<thead>
					<tr>
						<th data-options="field:'detailCode',width:100">ERP编码</th>
						<th data-options="field:'detailName',width:400">ERP名称</th>
						<th data-options="field:'dimCode',width:100">HFM编码</th>
						<th data-options="field:'dimName',width:400">HFM名称</th>
						<th data-options="field:'createDate',width:120">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
	
		function queryData(){

					var queryParams = $('#log3').datagrid('options').queryParams;  
					
					queryParams.dimMappingType = $("#dimMappingType").combobox('getValue');
					
					$('#log3').datagrid('reload'); 
				}
				
	    $("#export1").click(function(){
		  var url="../../download/ACCOUNT/ACCOUNT";
          window.open(url);
		});
		
		$("#export2").click(function(){
		  var url="../../download/Detail_01/Dim_01";
          window.open(url);
		});
		
		$("#export3").click(function(){
		  var url="../../download/Detail_02/Dim_02";
          window.open(url);
		});
		
		$("#export4").click(function(){
		  var url="../../download/Detail_03/Dim_04";
          window.open(url);
		});
		
		$("#export5").click(function(){
		  var url="../../download/Detail_04/Dim_04";
          window.open(url);
		});
		
				
	</script>
	</body>
</html>