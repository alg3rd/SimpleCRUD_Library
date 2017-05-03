<#import "/spring.ftl" as spring/>
<#import "shared.ftl" as shared/>
<#import "header.ftl" as header/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
<@shared.assets/>

</head>
<body>
<@header.block/>


<#--<div class="ui segment">-->
<#--<form action="reader/ajax/create" method="post" id="form" onsubmit="return false" class="ui form">-->
<#--<input type="text" value="Pavel" name="firstName">-->
<#--<input type="text" value="Potapov" name="lastName">-->
<#--<input type="text" value="LolStreet" name="address">-->
<#--<input type="text" value="375291491946" name="phone">-->
<#--<input type="submit" id="submit" class="ui button" value="Сохранить">-->
<#--</form>-->
<#--</div>-->
<div class="ui main container" style="padding-top: 100px">
    <h1 class="ui header">Читатели</h1>
    <p class="toolbar">
        <button id="button-create" class="ui button blue icon labeled">
            <i class="add icon"></i> Добавить
        </button>
    </p>
    <table id="table"
           data-toggle="table"
           data-url="reader/ajax/rows"
           data-pagination="false"
           data-classes="ui table compact striped">
        <thead>
        <tr>
            <th data-field="id" data-align="center">ID</th>
            <th data-field="lastName" data-align="center" data-formatter="fullName">Полное имя</th>
            <th data-field="address" data-align="center">Адрес</th>
            <th data-field="phone" data-align="center">Телефон</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
    <div class="ui modal" id="">
        <i class="close icon"></i>
        <div class="header">Читатель</div>
        <div class="content">
            <div class="container">
                <div class="ui form">
                    <form id="record" action="/path" method="post" <#--enctype="application/x-www-form-urlencoded"-->>
                        <input type="hidden" id="id" name="id" value="">
                        <div class="field required">
                            <label for="lastName">Фамилия:</label>
                            <input type="text" id="lastName" name="lastName" required>
                        </div>
                        <div class="field required">
                            <label for="firstName">Имя:</label>
                            <input type="text" id="firstName" name="firstName">
                        </div>
                        <div class="field">
                            <label for="patronymic">Отчество:</label>
                            <input type="text" id="patronymic" name="patronymic">
                        </div>
                        <div class="field">
                            <label for="address">Адрес:</label>
                            <input type="text" id="address" name="address">
                        </div>
                        <div class="field required">
                            <label for="phone">Телефон:</label>
                            <input type="text" id="phone" name="phone" required>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="actions">
            <div class="ui black deny button">Отмена</div>
            <div class="ui positive right labeled icon button" id="record-add-button">
                Сохранить
                <i class="checkmark icon"></i>
            </div>
        </div>
    </div>
</div>
<script src="js/readers.js"></script>
</body>
</html>