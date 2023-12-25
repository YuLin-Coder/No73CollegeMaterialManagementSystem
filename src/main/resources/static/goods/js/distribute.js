let tableIns;
let tableInsOnLine;
let tree;
layui.use(['element', 'form', 'table', 'layer', 'laydate', 'tree', 'util'], function () {
    let table = layui.table;
    let form = layui.form;//select、单选、复选等依赖form
    tree = layui.tree;
    let height = document.documentElement.clientHeight - 160;
    let laydate = layui.laydate


    //用户列表
    tableIns = table.render({
        elem: '#instructorTable'
        , url: ctx + '/distribute/page'
        , method: 'POST'
        //请求前参数处理
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'rows' //每页数据量的参数名，默认：limit
        }
        , response: {
            statusName: 'flag' //规定数据状态的字段名称，默认：code
            , statusCode: true //规定成功的状态码，默认：0
            , msgName: 'msg' //规定状态信息的字段名称，默认：msg
            , countName: 'records' //规定数据总数的字段名称，默认：count
            , dataName: 'rows' //规定数据列表的字段名称，默认：data
        }
        //响应后数据处理
        , parseData: function (res) { //res 即为原始返回的数据
            var data = res.data;
            return {
                "flag": res.flag, //解析接口状态
                "msg": res.msg, //解析提示文本
                "records": data.records, //解析数据长度
                "rows": data.rows //解析数据列表
            };
        }
        , toolbar: '#instructorTableToolbarDemo'
        , title: '物资发放列表'
        , cols: [[
            {type: 'numbers', title: '序号', width: 50}
            , {field: 'id', title: 'ID', hide: true}
            , {field: 'type', title: '类型'}
            , {field: 'goodsName', title: '名称'}
            , {field: 'count', title: '数量'}
            , {field: 'dept', title: '院部'}
            , {field: 'teacher', title: '老师'}
            , {fixed: 'right', title: '操作', toolbar: '#instructorTableBarDemo', width: 250}
        ]]
        , defaultToolbar: ['', '', '']
        , page: true
        , height: height
        , cellMinWidth: 80
    });

    //头工具栏事件
    table.on('toolbar(test)', function (obj) {
        switch (obj.event) {
            case 'addData':
                layui.use(['layer'], function () {
                    var layer = layui.layer, $ = layui.$;
                    layer.open({
                        type: 1,
                        title: "添加",
                        area: ['60%', '80%'],
                        content: $('#saveInstructor'),
                        scrollbar: false,
                        end: function () {
                            location.reload();  //关闭弹窗后刷新
                        }
                    });
                });

                //重置操作表单
                $("#instructorForm")[0].reset();
                form.render();


                break;
            case 'query':
                let type = $("#type").val();
                let goodsName = $("#goodsName").val();
                let dept = $("#dept").val();
                let teacher = $("#teacher").val();

                let query = {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , done: function (res, curr, count) {
                        //完成后重置where，解决下一次请求携带旧数据
                        // this.where = {};
                    }
                };
                if (!type) {
                    type = "";
                }
                if (!goodsName) {
                    goodsName = "";
                }
                if (!teacher) {
                    teacher = "";
                }
                if (!dept) {
                    dept = "";
                }
                //设定异步数据接口的额外参数
                query.where = {type:type,goodsName:goodsName,dept:dept,teacher:teacher};
                tableIns.reload(query);
                $("#type").val(type);
                $("#goodsName").val(goodsName);
                $("#dept").val(dept);
                $("#teacher").val(teacher);

                break;
            case 'reload':
                tableInsOnLine.reload();
                break;
        }
    });

//监听行工具事件
    table.on('tool(test)', function (obj) {
        let data = obj.data;
        if (obj.event === 'ck') {
            layer.confirm('确认出库吗？', function (index) {
                //向服务端发送删除指令
                $.post(ctx + "/distribute/ck/" + data.id, {}, function (data) {
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

    form.on('select(dept)', function(data){
        $.get(ctx + "/teacher/query?dept="+data.value,{},function (data) {
            var list = data.data;
            var select = document.getElementById('teacherId');
            if (list != null || list.size() > 0) {
                for (var c in list) {
                    var option = document.createElement("option");
                    option.setAttribute("value", list[c].id);
                    option.innerText = list[c].userName;
                    select.appendChild(option)
                }
            };
            form.render('select');
        },"json");
    });
});


/**
 * 提交保存
 */
function saveInstructor() {
    let instructorForm = $("#instructorForm").serializeObject();
    $.post(ctx + "/distribute/save", instructorForm, function (data) {
        if(!data.flag){
            layer.msg(data.msg, {icon: 2,time: 2000}, function () {});
            return;
        }

        layer.msg("保存成功", {icon: 1, time: 2000}, function () {});
        tableIns.reload();
    });
}
