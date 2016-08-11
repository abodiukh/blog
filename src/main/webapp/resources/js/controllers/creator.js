app.controller('postCreator', function($rootScope, $scope, $element, $location, $http, $window) {

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
            url: '/post',
            data: $scope.post,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            $window.location.href = '/post/' + data
        }).error(function(data, status, headers, config){
            if (status==401) {
                $rootScope.$emit('login', false);
            }
        });
    };

});