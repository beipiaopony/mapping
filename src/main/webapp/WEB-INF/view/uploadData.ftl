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
	
	<body style="padding:4px; margin:2px;">
       
        <div style="margin:4px 0;"></div>
		<div class="easyui-panel" title="上传数据" style="width:430px">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" onsubmit="return check()">
	        <input type="hidden" name="userName" id="userName" value="${userName!"null"}" />
	        <input type="hidden" name="appName" id="appName" value="${appName!"null"}" />        
	    	<table cellpadding="6">
	    	
	    	    <tr>
	    			<td>上传单位:</td>
	    			<td><select class="easyui-combobox" style="width:200px" name="uploadEntity" id="uploadEntity"
	    			 data-options="panelWidth: 400,
	    			               url: '../../organizations/${appName!"null"}/${userName!"null"}',
	    			               valueField: 'orgCode',
	    			               textField: 'orgDesc',
	    			               onLoadSuccess: function () { 
												                    var data = $('#uploadEntity').combobox('getData')
												                    if (data.length > 0) {
												                        $('#uploadEntity').combobox('setValue', data[0].orgCode)
												                    } 
												                },
                                   editable:${ComBoxEditable!"false"},
                                   required:true">

	    			</select></td>	    			
	    			
	    		</tr>

	    		
	    		<tr>
	    			<td>上传年度:</td>
	    			<!--  valuefield="flexValueId" textfield="description"  -->
	    			<td><select class="easyui-combobox" style="width:200px" name="uploadYear" id="uploadYear" data-options="required:true">
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
                        </select></td>
	    		</tr>
	    		<tr>
	    			<td>上传期间:</td>
	    			<td><select class="easyui-combobox" style="width:200px" name="uploadMonth" data-options="required:true">
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
                        </select></td>
	    		 </tr>
	    		
	    		<tr>
	    			<td>上传情景:</td>
	    			<td><select class="easyui-combobox" style="width:200px" name="uploadScenario" name="uploadScenario" data-options="required:true">
	    			             <option value="Actual">月报</option>
                                 <option value="Quarter">季报</option>
                                 <option value="Annual">年报</option>
	    			    </select>
	    			</td>
	    		</tr>
		    		<td>上传报表:</td>
		    			<td><select class="easyui-combogrid" name="uploadTables" style="width:200px" data-options=" 
									panelWidth: 300,
									multiple: true,
									idField: 'schemeId',
									textField: 'schemeName',
									url: '../../interfaceTables',
									columns: [[
										{field:'chk',checkbox:true},
										{field:'schemeId',title:'报表id',width:40},
										{field:'schemeName',title:'报表名称',width:160}
									]],
									fitColumns: true,
									required:true
								">
							</select>
	    			</td>
	    		<tr>
	
	    		<tr>
	    			<td>加载模式:</td>
	    			<td><select class="easyui-combobox" style="width:200px" name="uploadModel" id="uploadModel" data-options="required:true">
	    			             <option value="1" selected>合并</option>
                                 <option value="2">替换</option>
	    			    </select>
	    			</td>
	    		</tr>
	    		
	  
	    	</table>

	    </form>
	    
	   	<div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">上传</a>
	    	<td>&nbsp;</td>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()">重置</a>
	    </div> 
	</div>
	
	 	
		<script type="text/javascript">
		
			$('#ff').validatebox({
			    required: true
			});
			
			function check() {
			    return $("#ff").form('validate')
			}
		
						
			function submitForm(){

				 $('#ff').submit();
			}
			
			function clearForm(){
				$('#ff').form('clear');
			}
			
			//$(function(){
			    //onLoadSuccess:function(data){ ur=$("#userName").val();$('#uploadEntity').combobox('setValue',ur);}, 
			    // alert($("#userName").val())
			
			//});
			
			$(document).ready(function(){
			　　var mydate = new Date();
                 var str = "" + mydate.getFullYear();
			     //alert(str);
			     $('#uploadYear').combobox('setValue',str);
			}); 
						
	</script>
	
	</body>
</html>