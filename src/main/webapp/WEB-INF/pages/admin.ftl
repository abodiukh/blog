<#include "main.ftl">
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/admin.css">
</head>

<@renderPage>
<div id="content">
    <div class="manager" ng-controller="usersManager" ng-init="init()">
        <h2>Users</h2>
        <hr>
        <table>
            <thead>
                <tr>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Role</th>
                    <th>Enabled</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="user in users" ng-include="getTemplate(user)">
                </tr>
            <tbody>
        </table>
        <script type="text/ng-template" id="display">
            <td class="email">{{user.email}}</td>
            <td>{{user.name}}</td>
            <td>{{user.role}}</td>
            <td>{{user.enabled}}</td>
            <td><div class="button-list">
                <button class="btn-secondary" ng-click="editUser(user)">Edit</button>
            </td></div>
        </script>
        <script type="text/ng-template" id="edit">
            <td class="email">{{user.email}}</td>
            <td>{{user.name}}</td>
            <td><select class="input-control" ng-model="selectedUser.role" required>
                <option ng-repeat="role in roles" value="{{role.name}}">{{role.name}}</option>
            </select></td>
            <td><input type="checkbox" class="input-control checkbox" ng-model="selectedUser.enabled" /></td>
            <td>
                <div class="button-list">
                    <button class="btn-secondary" ng-click="saveUser($index)">Save</button>
                    <button class="btn-secondary" ng-click="reset()">Cancel</button>
                <div>
            </td>
        </script>
        <h2>Roles</h2>
        <hr>
        <table>
            <thead>
            <tr>
                <th style="width: 30%">Role</th>
                <th style="width: 30%">Rights</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="role in roles" ng-include="getRoleTemplate(role)">
            </tr>
            <tbody>
        </table>
        <script type="text/ng-template" id="displayRole">
            <td style="width: 30%">{{role.name}}</td>
            <td style="width: 30%">{{role.rights.length}}</td>
            <td><div class="button-list">
                <button class="btn-secondary" ng-click="editRole(role)">Edit</button>
            </td></div>
        </script>
        <script type="text/ng-template" id="editRole">
            <td style="width: 30%">{{role.name}}</td>
            <td style="width: 30%">
                <div class="ddmulti" ng-dropdown-multiselect="" options="rightsData" selected-model="rightsModel"
                     extra-settings="settings" translation-texts="customTexts">
                </div>
            </td>
            <td>
                <div class="button-list">
                    <button class="btn-secondary" ng-click="saveRole($index)">Save</button>
                    <button class="btn-secondary" ng-click="resetRole()">Cancel</button>
                    <div>
            </td>
        </script>
    </div>
</div>
</@renderPage>