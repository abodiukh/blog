app.controller('loginController', function($rootScope, $scope, $http) {

    $scope.user = {username: "", password: ""};

    $scope.login = function() {

        $http({
            method: 'POST',
            url: '/user/login',
            data: $scope.user,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            if (status==200) {
                $rootScope.$emit('login', true);
            }
            $scope.user = {username: "", password: ""};
        })
        .error(function(data, status, headers, config){
            if (status==401) {
                $rootScope.$emit('login', false);
            }
        })
    };

    $scope.logout = function() {

        $http({
            method: 'POST',
            url: '/user/logout',
            data: '',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {

        })
        .error(function(data, status, headers, config){

        })
    };

});