<header ng-controller="expandController">
    <div class="top-navigation">
        <div class="top-navigation-left">
            <a href="/post/all">Blog</a>
        </div>
        <div class="top-navigation-right" ng-controller="loginController">
            <a ng-click="expand()">Sign in</a>
            <a ng-click="logout()">Sign out</a>
        </div>
    </div>

    <div id="login-box" ng-show="isExpanded">
        <div id="inner-box">
            <div id="login" ng-controller="loginController">
                <form class="login-form" ng-submit="login()">
                    <h2 ng-show="invalid">Invalid login or password</h2>
                    <label for="inputUsername" class="input-field">Username</label>
                    <input type="text" id="inputUsername" class="form-control"
                           placeholder="Username" required autofocus ng-model="user.username">
                    <label for="inputPassword" class="input-field">Password</label>
                    <input type="text" id="inputPassword" class="form-control"
                           placeholder="Password" required autofocus ng-model="user.password">
                    <br>
                    <button class="btn btn-primary btn-block" type="submit">Sign in</button>
                </form>
            </div>
        </div>
    </div>
</header>