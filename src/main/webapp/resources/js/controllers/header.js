app.controller('expandController', function ($rootScope, $scope, $element) {

    this.isExpanded = false;
    this.invalid = false;

    $scope.expand = function () {
        $scope.isExpanded = !$scope.isExpanded;
    }

    $rootScope.$on('login', function (event, args) {
        $scope.isExpanded = !args;
        $scope.invalid = !args;
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