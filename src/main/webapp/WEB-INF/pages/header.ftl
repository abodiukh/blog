<header>
    <div class="intro">
        <h1><a href="/post/all">Blog</a></h1>
    </div>
    <div id="login" ng-controller="loginController">
        <form class="login-form" ng-submit="postForm()">
            <div ng-controller="expandController">
                <h1 ng-click="expand()">Sign in</h1>
                <div id="login-box" ng-show="isExpanded">
                    <div id="inner-box">
                        <label for="inputUsername" class="input-field">Username</label>
                        <input type="text" id="inputUsername" class="form-control"
                               placeholder="Username" required autofocus ng-model="user.username">
                        <label for="inputPassword" class="input-field">Password</label>
                        <input type="text" id="inputPassword" class="form-control"
                               placeholder="Password" required autofocus ng-model="user.password">
                        <br>
                        <button class="btn btn-primary btn-block" type="submit">Sign in</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</header>