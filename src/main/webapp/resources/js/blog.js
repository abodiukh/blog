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
    $scope.textModel = "";
    $scope.post = $element[0].querySelector(".post");

    $scope.id = location.pathname.split('/')[2];
    $scope.author = "Andrii";

    $scope.turnEditMode = function() {
        $scope.textModel = $scope.post.innerHTML.trim();
        $scope.editMode = true;
        $scope.viewMode = false;
    };

    $scope.turnPreviewMode = function() {
        angular.element($scope.post).html('').append($scope.textModel);
        $scope.editMode = false;
        $scope.viewMode = true;
    }

    $scope.savePost = function() {

        this.data = {
            id: $scope.id,
            author: $scope.author,
            text: $scope.textModel
        };

        $http({
            method: 'PUT',
            url: '',
            data: this.data,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
    }

});

app.controller('postCreator', function($scope, $element, $location, $http) {

    $scope.isExpanded = false;
    $scope.plusIcon = true;
    $scope.minusIcon = false;

    $scope.expand = function() {
        $scope.isExpanded = !$scope.isExpanded;
        $scope.plusIcon = !$scope.plusIcon;
        $scope.minusIcon = !$scope.minusIcon;
    }

    $scope.addPost = function() {

        $scope.title = "";
        $scope.post = {
            id: 0,
            author: {
                userId: "0"
            },
            title: "Some text"
        };

        $http({
            method: 'POST',
            url: 'add',
            data: $scope.post,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
    }

});


