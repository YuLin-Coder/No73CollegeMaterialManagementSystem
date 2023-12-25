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
        , url: ctx + '/teacher/page'
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
        , title: '教师列表'
        , cols: [[
            {type: 'numbers', title: '序号', width: 50}
            , {field: 'id', title: 'ID', hide: true}
            , {field: 'userName', title: '姓名'}
            , {field: 'loginName', title: '工号'}
            , {field: 'dept', title: '院部'}
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
                        title: "添加老师",
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
                let loginName = $("#loginName").val();
                let query = {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , done: function (res, curr, count) {
                        //完成后重置where，解决下一次请求携带旧数据
                        // this.where = {};
                    }
                };
                if (!loginName) {
                    loginName = "";
                }
                //设定异步数据接口的额外参数
                query.where = {loginName:loginName};
                tableIns.reload(query);
                $("#loginName").val(loginName);
                break;
            case 'reload':
                tableInsOnLine.reload();
                break;
        }
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) {
        let data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                //向服务端发送删除指令
                $.delete(ctx + "/teacher/delete/" + data.id, {}, function (data) {
                    tableIns.reload();
                    layer.close(index);
                })
            });
        } else if (obj.event === 'edit') {
            layui.use(['layer'],function (index) {
                var layer = layui.layer,$=layui.$;
                layer.open({
                    type: 1,
                    title: "编辑",
                    area: ['60%', '80%'],
                    content: $('#saveInstructor'),
                    scrollbar: false,
                    end: function () {
                        location.reload();  //关闭弹窗后刷新
                    }
                });
            });
            //回显操作表单
            $("#instructorForm").form(data);
            form.render();
        }
    });

});


/**
 * 提交保存
 */
function saveInstructor() {
    let instructorForm = $("#instructorForm").serializeObject();
    $.post(ctx + "/teacher/save", instructorForm, function (data) {
        if(!data.flag){
            layer.msg(data.msg, {icon: 2,time: 2000}, function () {});
            return;
        }

        layer.msg("保存成功", {icon: 1, time: 2000}, function () {});
        tableIns.reload();
    });
}
