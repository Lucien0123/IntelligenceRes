/**
 * Created by Administrator on 2016/11/7.
 */
/*$(document).ready(function () {
 var flag = 1;
 var endFlag = 20;
 var htmlContent = "";
 while (flag.gt(endFlag)) {
 htmlContent += "<option value='" + flag + "'>第" + flag + "周</option>";
 flag++;
 }
 $("#start_week").append(htmlContent);
 });
 $("#start_week").click(function () {
 $("#end_week").empty();
 $("#end_week").append("<option>请选择结束上课周次</option>");
 var flag = $("#start_week").val();
 var endFlag = 20;
 var htmlContent = "";
 while (flag.gt(endFlag)) {
 htmlContent += "<option value='" + flag + "'>第" + flag + "周</option>";
 flag++;
 }
 $("#end_week").append(htmlContent);
 });

 $(".courseItem").click(function () {
 var startContent = "<option th:text='"+ 1 +"' th:value='" + 1 + "' selected='selected'>第一周</option>";
 var endContent="<option th:text='"+ 1 +"' th:value='" + 1 + "' selected='selected'>第一周</option>";
 var sectionContent = "";
 var weekContent = "";
 var sectionflag = 1;   //节次
 var weekflag = 1;      //周次
 /!* 设定节次的选项--单选 *!/
 while (sectionflag.gt(5)){
 if(sectionflag == $(this).find(".csection").val()){
 sectionContent += "<label class='radio-inline'><input type='radio' " +
 "name='section' value='1' checked='checked'/>第" + sectionflag + "大节</label>";
 } else {
 sectionContent += "<label class='radio-inline'><input type='radio' " +
 "name='section' value='1'/>第" + sectionflag + "大节</label>";
 }
 sectionflag++;
 }
 /!* 设定周次的选项--多选 *!/
 while (weekflag.gt(6)){
 if($(this).find(".cdayweek").val().indexOf(weekflag) >= 0){
 weekContent += "<label class='checkbox-inline'><input type='checkbox' " +
 "name='day_for_week' value='1' checked='checked'/>星期" + weekflag + "</label>";
 } else {
 weekContent += "<label class='checkbox-inline'><input type='checkbox' " +
 "name='day_for_week' value='1'/>星期" + weekflag + "</label>";
 }
 weekflag++;
 }

 $("#name").val();
 $("#start_week").append(startContent);
 $("#end_week").append(endContent);
 $("#sectionGroup").append();
 $("#weekGroup").append();
 return false;
 });*/