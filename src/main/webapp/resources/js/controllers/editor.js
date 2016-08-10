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
    };

    $scope.savePost = function() {

        $http({
            method: 'PUT',
            url: '',
            data: $scope.postData,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    };

    $scope.publishPost = function() {

        $http({
            method: 'POST',
            url: location.pathname + '/publish',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    };

    $scope.unpublishPost = function() {

        $http({
            method: 'POST',
            url: location.pathname + '/unpublish',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    };

    $scope.deletePost = function() {

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
        });
    };

});