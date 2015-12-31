var app = angular.module('login', []);
app.controller('loginController', function($scope, $http) {

    $scope.user = {};

    $scope.postForm = function() {

        $http({
            method: 'POST',
            url: 'http://localhost:9090/user/login',
            data: $scope.user,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {

        })
        .error(function(data, status, headers, config){

        })
    }
});