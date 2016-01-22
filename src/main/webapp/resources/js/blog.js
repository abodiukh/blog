var app = angular.module('blog', []);
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

app.controller('buttonManager', function($scope) {

    this.editMode = false;

    $scope.turnEditMode = function() {
        $scope.editMode = true;
    };

    $scope.turnPreviewMode = function() {
        $scope.editMode = false;
    }

});

