$(function () {
    window.addEventListener('load', function(){
        $('.ui.typical.dropdown').dropdown();
        $('.ui.checkbox').checkbox();
    });
    function is(mWhat, sTypes) {
        var
            k, l, c;
        if (typeof sTypes != 'string') {
            throw new TypeError();
        }
        for (k = 0, l = sTypes.length; k < l; k++) {
            c = sTypes[k];
            //noinspection FallThroughInSwitchStatementJS
            switch (c) {
                case 'a'://array
                    if (Array.isArray(mWhat)) {
                        return c;
                    }
                    break;
                case 'o'://object
                    if (!mWhat) {//workaround for (typeof null == 'object')
                        continue;
                    }
                case 's'://string
                case 'n'://number
                case 'f'://function
                case 'b'://boolean
                case 'u'://undefined
                    if ((typeof mWhat)[0] === c) {
                        return c;
                    }
                    break;
                case ' '://null
                    if (mWhat === null) {
                        return c;
                    }
                    break;
                case 'S'://non-empty string
                    if ((typeof mWhat)[0] === 's' && mWhat) {
                        return 'S';
                    }
                    break;
                case 'N'://valid number (non-NaN, finite)
                    if ((typeof mWhat)[0] === 'n' && !isNaN(mWhat) && isFinite(mWhat)) {
                        return 'N';
                    }
                    break;
                case 'X'://XML-node
                case 'H'://HTML-node
                    if (mWhat instanceof Node) {
                        if (c == 'H') {
                            if (mWhat.namespaceURI == document.namespaceURI) {
                                return c;
                            }
                        }
                        else {
                            if (mWhat.namespaceURI != document.namespaceURI) {
                                return c;
                            }
                        }
                        return c;
                    }
                    break;
                case 'R'://RegExp
                    if (mWhat instanceof RegExp) {
                        return c;
                    }
            }
        }
        return null;
    }

    if ('moment' in window) {
        moment.locale('ru');
    }
    $('.bs-table').on('editable-save.bs.table', function () {
        var table = $(this);
        var actual = arguments[2];//var old = arguments[3];
        $.ajax({
            url: $(this).data('editable-url'),
            type: 'post',
            data: actual,
            dataType: 'json',
            success: function (data) {
                if (data.error) {
                    alert(data.error);
                    table.bootstrapTable('refresh');
                }
                else {
                    table.bootstrapTable('refresh', {silent: true});
                }
            }
        });
    });

    function cnvObject2PostRecourse(oData, aResult, sPrefix, oOptions) {
        var
            k, l;
        switch (is(oData, 'aosnb u')) {
            case 'a':
                for (k = 0, l = oData.length; k < l; k++) {
                    cnvObject2PostRecourse(
                        oData[k],
                        aResult,
                        sPrefix
                            ? sPrefix + '[]'
                            : encodeURIComponent(k + ''),
                        oOptions
                    );
                }
                break;
            case 'o':
                for (k in oData) {
                    if (oData.hasOwnProperty(k)) {
                        cnvObject2PostRecourse(
                            oData[k],
                            aResult,
                            sPrefix
                                ? sPrefix + '[' + encodeURIComponent(k) + ']'
                                : encodeURIComponent(k),
                            oOptions
                        );
                    }
                }
                break;
            case 's':
            case 'n':
            case 'b':
                return aResult.push(sPrefix + '=' + encodeURIComponent(oData + ''));
            case ' ':
                return aResult.push(sPrefix + '=' + encodeURIComponent(oOptions.defaultNull || defaults.null));
            case 'u':
                return aResult.push(sPrefix + '=' + encodeURIComponent(oOptions.defaultUndefined || defaults.undefined));
        }
    }

    function cnvObject2Post(oData, oOptions) {
        var
            result = [];
        cnvObject2PostRecourse(oData, result, '', oOptions || {});
        return result.join('&');
    }

    function cnvForm2Object(hNode, oOptions) {

        var
            list = hNode.querySelectorAll('input,select,textarea'),
            data = {}, c, q,
            k = 0, l = list.length;
        for (; k < l; k++) {
            c = list[k];
            if (
                c.disabled === true && !oOptions.includeDisabled ||
                c.offsetHeight === 0 && !oOptions.includeNonVisible && c.type !== 'hidden' || !c.name
            ) {
                continue;
            }
            if (c.type === 'radio') {
                if (!data[c.name]) {
                    data[c.name] = [];
                }
                data[c.name].push(c.value);
            }
            else if (c.nodeName.toLowerCase() == 'select' && c.multiple) {
                if (!data[c.name]) {
                    data[c.name] = [];
                }
                for (q = c.selectedOptions.length; q--;) {
                    data[c.name].push(c.selectedOptions[q].value);
                }

            }
            else {
                data[c.name] = c.type == "checkbox" ? c.checked : c.value;
            }

        }
        // console.log(data);
        return data;
    }

    window.serializeObject = function serializeObject(result) {
        return cnvObject2Post(result)
    };
    window.serializeForm = function (hForm, oOptions) {
        return cnvForm2Object(hForm, oOptions || {});
    };
    function serializeForm(hRoot) {
        var
            result = {},
            list = [],
            k,
            inputs = hRoot.getElementsByTagName('input'),
            selects = hRoot.getElementsByTagName('select'),
            textareas = hRoot.getElementsByTagName('textarea')
        for (k = inputs.length; k--;) {
            list.push(inputs[k]);
        }
        for (k = selects.length; k--;) {
            list.push(selects[k]);
        }
        for (k = textareas.length; k--;) {
            list.push(textareas[k]);
        }
        for (k = list.length; k--;) {
            if ($(list[k]).is(':visible') || list[k].type == 'hidden') {
                if (['checkbox', 'radio'].indexOf(list[k].type) !== -1) {
                    result[list[k].name] = list[k].checked;
                }
                else {
                    result[list[k].name] = list[k].value;
                }
            }
        }
        return result;
    }

});

$(function () {
    if ($.fn.datetimepicker) {
        $.datetimepicker.setLocale('ru');
        $('.datepicker').datetimepicker(
            {
                timepicker: false,
                format: 'd.m.Y',
                dayOfWeekStart: 1
            }
        );
        $('.datetimepicker').datetimepicker(
            {
                timepicker: true,
                format: 'd.m.Y H:i',
                dayOfWeekStart: 1
            }
        );
    }
    $('.filter-bs_table').on('keypress', function refreshTableDelayed(event) {
        var
            target = event.target;
        if (target._timeoutId) {
            clearTimeout(target._timeoutId);
            target._timeoutId = 0;
        }
        target._timeoutId = setTimeout(function () {
            target._timeoutId = 0;
            $('#table').bootstrapTable('refresh');
        }, 300);
    });

    $('.only-date, .only-phone, .only-numeric').on('keypress', function (e) {
        var
            r = /\bonly-(\S+)\b/.exec(e.target.className);
        if (r) {
            var
                test = ({
                    'phone': /[\d+]/,
                    'date': /[\d.]/,
                    'numeric': /\d/
                })[r[1]];

            if (test && !test.test(String.fromCharCode(e.charCode))) {
                e.preventDefault();
            }
        }
    });
});