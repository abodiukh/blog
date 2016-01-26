var app = angular.module('blog', ['ngSanitize']);
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

app.controller('expandController', function($scope) {

    this.isExpanded = false;

    $scope.expand = function() {
        $scope.isExpanded = !$scope.isExpanded;
    }

});

app.controller('buttonManager', function($scope, $element, $location, $http) {

    $scope.editMode = false;
    $scope.viewMode = true;

    $scope.postData = {
        text: "",
        title: ""
    };

    $scope.post = {
        title: $element[0].querySelector(".post h1 a"),
        text: $element[0].querySelector(".post p")
    };

    $scope.id = location.pathname.split('/')[2];

    $scope.turnEditMode = function() {
        $scope.postData.title = $scope.post.title.innerHTML.trim();
        $scope.postData.text = $scope.post.text.innerHTML.trim();
        $scope.editMode = true;
        $scope.viewMode = false;
    };

    $scope.turnPreviewMode = function() {
        angular.element($scope.post.title).empty().append($scope.postData.title);
        angular.element($scope.post.text).html('').append($scope.postData.text);
        $scope.editMode = false;
        $scope.viewMode = true;
    }

    $scope.savePost = function() {

        $http({
            method: 'PUT',
            url: '',
            data: $scope.postData,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
    }

    $scope.publishPost = function() {

        $http({
            method: 'POST',
            url: '/publish',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
    }

    $scope.publishPost = function() {

        $http({
            method: 'POST',
            url: 'unpublish',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
    }

    $scope.publishPost = function() {

        $http({
            method: 'DELETE',
            url: '',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            $window.location.href = '/post/all'
        })
    }

});

app.controller('postCreator', function($scope, $element, $location, $http, $window) {

    $scope.isExpanded = false;
    $scope.plusIcon = true;
    $scope.minusIcon = false;

    $scope.post = {
        author: "andrii",
        title: ""
    };

    $scope.expand = function() {
        $scope.isExpanded = !$scope.isExpanded;
        $scope.plusIcon = !$scope.plusIcon;
        $scope.minusIcon = !$scope.minusIcon;
    };

    $scope.addPost = function() {

        $http({
            method: 'POST',
            url: 'add',
            data: $scope.post,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            $window.location.href = '/post/' + data
        })
    }

});


