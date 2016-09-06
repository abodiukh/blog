<#include "main.ftl">
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/admin.css">
</head>

<@renderPage>
<div id="content">
    <div class="users-manager" ng-controller="usersManager" ng-init="init()">
        <h2>Users</h2>
        <hr>
        <table>
            <thead>
                <tr>
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
            <td>{{user.name}}</td>
            <td>{{user.role}}</td>
            <td>{{user.enabled}}</td>
            <td><div class="button-list">
                <button class="btn-secondary" ng-click="editUser(user)">Edit</button>
            </td></div>
        </script>
        <script type="text/ng-template" id="edit">
            <td>{{user.name}}</td>
            <td><select class="input-control" ng-model="selectedUser.role" ng-options="role for role in roles"></select></td>
            <td><input type="checkbox" class="input-control" ng-model="selectedUser.enabled" /></td>
            <td>
                <div class="button-list">
                    <button class="btn-secondary" ng-click="saveUser($index)">Save</button>
                    <button class="btn-secondary" ng-click="reset()">Cancel</button>
                <div>
            </td>
        </script>
    </div>
</div>
</@renderPage>