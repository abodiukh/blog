var app = angular.module('login', []);
app.controller('loginController', function($scope, $http) {

    $scope.user = {};

    $scope.postForm = function() {

        $http({
            method: 'POST',
            url: 'user/login',
            data: $scope.user,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        })
        .success(function(data, status, headers, config) {

        })
        .error(function(data, status, headers, config){

        })
    }
});