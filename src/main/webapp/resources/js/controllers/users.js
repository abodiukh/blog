app.controller('usersManager', function($rootScope, $scope, $element, $location, $http, $window) {

    $scope.users = {};
    $scope.roles = {};

    $scope.selectedUser = {};
    $scope.selectedRole = {};

    $scope.init = function() {

        $http({
            method: 'GET',
            url: '/admin/users',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            $scope.users = data;
        })
        .error(function(data, status, headers, config){
        });

        $http({
            method: 'GET',
            url: '/admin/roles',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            $scope.roles = data;
        })
        .error(function(data, status, headers, config){
        });

        $http({
            method: 'GET',
            url: '/admin/rights',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            $scope.rightsData = _.map(data, function(right, index) {
                return {id: index, name: right};
            });
        })
        .error(function(data, status, headers, config){
        })
    };

    //Users

    $scope.getTemplate = function (user) {
        if (user.id === $scope.selectedUser.id) return 'edit';
        else return 'display';
    };

    $scope.editUser = function (user) {
        $scope.selectedUser = angular.copy(user);
    };

    $scope.saveUser = function (idx) {
        console.log("Saving user");

        $http({
            method: 'PUT',
            url: '/admin/user',
            data: $scope.selectedUser,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
        })
        .error(function(data, status, headers, config){
        });

        $scope.users[idx] = angular.copy($scope.selectedUser);
        $scope.reset();
    };

    $scope.reset = function () {
        $scope.selectedUser = {};
    };


    //Roles

    $scope.getRoleTemplate = function (role) {
        if (role.name === $scope.selectedRole.name) return 'editRole';
        else return 'displayRole';
    };

    $scope.editRole = function (role) {
        $scope.selectedRole = angular.copy(role);
        $scope.rightsModel = _.map(role.rights, function(right) {
            return {id: right};
        });
    };

    $scope.saveRole = function (idx) {
        console.log("Saving role");
        $scope.selectedRole.rights = _.map($scope.rightsModel, function(right) {
           return right.id;
        });

        $http({
             method: 'PUT',
             url: '/admin/role',
             data: $scope.selectedRole,
             headers: {
                 'Accept': 'application/json',
                 'Content-Type': 'application/json'
             }
         })
         .success(function(data, status, headers, config) {
         })
         .error(function(data, status, headers, config){
         });

        $scope.roles[idx] = angular.copy($scope.selectedRole);
        $scope.resetRole();
    };

    $scope.resetRole = function () {
        $scope.selectedRole = {};
    };

    $scope.rightsModel = [];
    $scope.rightsData = {};
    $scope.settings = {displayProp: 'name', idProp: 'name'};

});