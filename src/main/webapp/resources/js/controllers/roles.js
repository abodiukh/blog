app.controller('rolesManager', function($rootScope, $scope, $element, $location, $http, $window) {

    $scope.roles = {};

    $scope.selectedRole = {};

    $scope.init = function() {

        /*$http({
            method: 'GET',
            url: '/admin/roles',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
            $scope.roles = data;
        })
        .error(function(data, status, headers, config){
        })*/
    };

    $scope.getRoleTemplate = function (role) {
        if (role.id === $scope.selectedRole.id) return 'editRole';
        else return 'displayRole';
    };

    $scope.editRole = function (role) {
        $scope.selectedRole = angular.copy(role);
    };

    $scope.saveRole = function (idx) {
        console.log("Saving contact");

        /*$http({
            method: 'PUT',
            url: '/admin/role',
            data: $scope.selectedRole,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .success(function(data, status, headers, config) {
        })
        .error(function(data, status, headers, config){
        });*/

        $scope.roles[idx] = angular.copy($scope.selectedRole);
        $scope.reset();
    };

    $scope.reset = function () {
        $scope.selectedRole = {};
    };

    $scope.rightsModel = [];
    $scope.rightsData = [ {id: 1, name: "read"}, {id: 2, name: "write"}, {id: 3, name: "create"}, {id: 4, name: "delete"}];
    $scope.settings = {displayProp: 'name', idProp: 'id'};
    $scope.customTexts = {buttonDefaultText: 'rights'};

});