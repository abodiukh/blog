app.service('auth', function($rootScope) {
    this.handleError = function() {
        $rootScope.$emit('login', false);
    }
});