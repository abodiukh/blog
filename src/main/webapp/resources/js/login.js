angular.module('login', [])
.controller('loginController', ['$scope', '$http', function($scope, $http) {
    this.postForm = function() {
        var encodeedString = 'username=' + encodeURIComponent(this.inputData.username) +
                             '&password=' + encodeURIComponent(this.inputData.password);
        $http({
            method: 'POST',
            url: 'login',
            data: encodeedString,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        })
        .success(function(data, status, headers, config) {

        })
        .error(function(data, status, headers, config){

        })
    }
}]);