app.controller('loginController', function ($rootScope, $scope, $http, $element, $window) {

    this.isExpanded = false;
    this.isRegistration = false;
    this.isAuthorized = false;

    $scope.user = {};

    $scope.init = function() {

        $http({
            method: 'POST',
            url: '/user/isAuthorized',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            $scope.isAuthorized = true;
        })
        .error(function(data, status, headers, config){
            $scope.isAuthorized = false;
        })
    };

    $scope.login = function () {
        if ($scope.isAuthorized) {
            $scope.logout();
        } else {
            $scope.isExpanded = !$scope.isExpanded;
            $scope.isRegistration = false;
            $scope.invalid = false;
        }
    };

    $scope.register = function () {
        $scope.isExpanded = false;
        $scope.invalid = false;
        $scope.isRegistration = !$scope.isRegistration;
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
            $scope.isAuthorized = false;
            $window.location.href = '/post/all'
        })
        .error(function(data, status, headers, config){
            $scope.isAuthorized = true;
        })
    };

    $scope.createAccount = function() {
        $http({
            method: 'POST',
            url: '/user/registration',
            data: $scope.user,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            if (status==200) {
                $scope.invalid = false;
                $window.location.reload();
            }
        })
        .error(function(data, status, headers, config){
            $scope.invalid = true;
            $scope.errorMessage = data.join(", ");
        })
    };

    $scope.authorize = function() {

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
                $window.location.reload();
                $rootScope.$emit('login', [true, '']);
            }
            $scope.user = {name: "", password: ""};
        })
        .error(function(data, status, headers, config){
            var message = '';
            if (status==401) {
                message = "You haven't been logged in"
            } else if (status == 403) {
                message = "Invalid login or password"
            }
            $rootScope.$emit('login', [false, message]);
        })
    };

    $rootScope.$on('login', function (event, args) {
        $scope.isExpanded = !args[0];
        $scope.isAuthorized = args[0];
        $scope.invalid = !args[0];
        $scope.errorMessage = args[1];
    });

    var header = $element[0];

    //TODO debounce!!!!!
    //scrolling
    var lastScrollTop = 0;

    function scrollHandler() {
        var st = window.pageYOffset || document.documentElement.scrollTop;

        if (st > lastScrollTop || st == 0) {
            // downscroll code
            header.classList.remove('fixed');
        } else {
            // upscroll code
            header.classList.add('fixed');
        }
        lastScrollTop = st;
    }

    window.addEventListener("scroll", scrollHandler, false);

});