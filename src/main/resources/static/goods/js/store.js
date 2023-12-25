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
        , url: ctx + '/store/page'
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
        , title: '库存信息'
        , cols: [[
            {type: 'numbers', title: '序号', width: 50}
            , {field: 'id', title: 'ID', hide: true}
            , {field: 'goodsName', title: '名称'}
            , {field: 'count', title: '数量', templet:function (data) {
                if(data.count < 5) {
                    return ' <span style="color: red;">' + data.count + '</span>'
                } else {
                    return ' <span style="color: blue;">' + data.count + '</span>'
                }
                }}
        ]]
        , defaultToolbar: ['', '', '']
        , page: true
        , height: height
        , cellMinWidth: 80
    });

    //头工具栏事件
    table.on('toolbar(test)', function (obj) {
        switch (obj.event) {
            case 'query':
                let goodsName = $("#goodsName").val();
                let query = {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , done: function (res, curr, count) {
                        //完成后重置where，解决下一次请求携带旧数据
                        // this.where = {};
                    }
                };
                if (!goodsName) {
                    goodsName = "";
                }
                //设定异步数据接口的额外参数
                query.where = {goodsName:goodsName};
                tableIns.reload(query);
                $("#goodsName").val(goodsName);

                break;
            case 'reload':
                tableInsOnLine.reload();
                break;
        }
    });


});

