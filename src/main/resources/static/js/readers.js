function fullName(value, row) {
    return row.lastName + ' ' + row.firstName + ' ' + row.patronymic;
}
//        function operateFormatter(value, row, index) {
//            return [
//                '<div class="ui large labels">',
//                '<a class="ui icon button blue" onclick="startEditing(' + row.id + ')"><i class="configure icon"></i></a>',
//                '<form method="post" id="form' + row.id + '"action="{{ path('centreon_remove_$id', {id : '__id__'}) }}" '.replace('__id__', row.id) +
//                'style="display: inline-block;" onsubmit="return confirm(\'Вы уверены?\');">',
//                '<input type="hidden" name="id" value="' + row.id + '"/>',
//                '<a class="ui icon button red" type="submit" onclick="submitForm(' + row.id + ')"><i class="ban icon"></i></a>',
//                '</form>',
//                '</labels>'
//            ].join('');
//        }
function submitForm(id) {
    $('#form' + id).submit();
}


$(function () {
    var
        dlg = $('.ui.modal'),
        table = $('#table'),
        status = $('#check_status'),
        usersToCall = $('#usersToCall'),
        usersToSms = $('#usersToSms');

    function fillFields(oData) {
        $('#record-ip').val(oData.ip);
        $('#record-id').val(oData.id);
        $('#record-service').val(oData.service);

        status.dropdown('clear');
        status.dropdown('set value', oData.checkStatus);
        usersToCall.dropdown('clear');
        usersToCall.dropdown('set value', oData.usersToCall);
        usersToSms.dropdown('clear');
        usersToSms.dropdown('set value', oData.usersToSms);
    }


    window.startEditing = function startEditing(id) {
        var row = table.bootstrapTable('getRowByUniqueId', id);
        fillFields(row instanceof $ ? {} : row);
        dlg.modal('show')
    };
    $('#record-add-button').click(function () {
        var edit_url = '';
        var create_url = '/reader/ajax/create';
        var temp = serializeForm(document.getElementById('record'), {includeNonVisible: true});
        sendData(temp.id > 0 ? edit_url.replace('__id__', temp.id) : create_url, temp);
        dlg.modal('hide');
    });
    $('#button-create').click(function () {
        fillFields({});
        dlg.modal('show')
    });
    function sendData(url, oData) {
        $.ajax({
            url: url,
            type: 'post',
            data: JSON.stringify({
                firstName: oData.firstName,
                lastName: oData.lastName,
                patronymic: oData.patronymic,
                address: oData.address,
                phone: oData.phone
            }),
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            success: function () {
                table.bootstrapTable('refresh');
            }
        });
    }
}());
