<header ng-controller="loginController">
    <div class="top-navigation">
        <div class="top-navigation-left">
            <a href="/post/all">Blog</a>
        </div>
        <div class="top-navigation-right">
            <a ng-click="login()">Sign {{isAuthorized ? 'out' : 'in'}}</a>
            <#--<a ng-click="logout()">Sign out</a>-->
        </div>
    </div>

    <div id="login-box" ng-show="isExpanded">
        <div id="inner-box">
            <div id="login">
                <form class="login-form" ng-submit="authorize()">
                    <h2 ng-show="invalid">{{errorMessage}}</h2>
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