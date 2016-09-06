app.controller('usersManager', function($rootScope, $scope, $element, $location, $http, $window) {

    $scope.users = {};
    $scope.roles = {};

    $scope.selectedUser = {};

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
        })
    };

    $scope.getTemplate = function (user) {
        if (user.id === $scope.selectedUser.id) return 'edit';
        else return 'display';
    };

    $scope.editUser = function (user) {
        $scope.selectedUser = angular.copy(user);
    };

    $scope.saveUser = function (idx) {
        console.log("Saving contact");

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

});