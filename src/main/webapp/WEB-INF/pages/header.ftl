<#-- @ftlvariable name="permissions" type="java.util.List<java.lang.String>" -->

<header ng-controller="loginController" ng-init="init()">
    <div class="top-navigation">
        <div class="top-navigation-left">
            <img src="/resources/images/logo.svg">
            <a href="/post/all">Blog</a>
            <#assign isAdmin>${(permissions?? && permissions?seq_contains('administer'))?c}</#assign>
            <a href="/admin" ng-if="${isAdmin}">Admin</a>
        </div>
        <div class="top-navigation-right">
            <a ng-click="login()">Log {{isAuthorized ? 'out' : 'in'}}</a>
            <a ng-click="register()">Sign up</a>
        </div>
    </div>

    <div id="login-box" ng-if="isExpanded">
        <div id="inner-box">
            <div id="login">
                <form class="login-form" ng-submit="authorize()">
                    <h2 ng-show="invalid">{{errorMessage}}</h2>
                    <label for="inputUsername" class="input-field">Username</label>
                    <input type="text" id="inputUsername" class="form-control"
                           placeholder="Username" required autofocus ng-model="user.name">
                    <label for="inputPassword" class="input-field">Password</label>
                    <input type="password" id="inputPassword" class="form-control"
                           placeholder="Password" required autofocus ng-model="user.password">
                    <br>
                    <button class="btn btn-primary btn-block" type="submit">Sign in</button>
                </form>
            </div>
        </div>
    </div>
    <div id="sign-box" ng-if="isRegistration">
        <div id="inner-box">
            <div id="sign">
                <form class="sign-form" ng-submit="createAccount()">
                    <h2 ng-show="invalid">{{errorMessage}}</h2>
                    <label for="inputEmail" class="input-field">Email</label>
                    <input type="text" id="inputEmail" class="form-control"
                           placeholder="Email" required autofocus ng-model="user.email">
                    <label for="inputUsername" class="input-field">Username</label>
                    <input type="text" id="inputUsername" class="form-control"
                           placeholder="Username" required autofocus ng-model="user.name">
                    <label for="inputPassword" class="input-field">Password</label>
                    <input type="password" id="inputPassword" class="form-control"
                           placeholder="Password" required autofocus ng-model="user.password">
                    <label for="inputConfirmedPassword" class="input-field">Password</label>
                    <input type="password" id="inputConfirmedPassword" class="form-control"
                           placeholder="Confirmed password" required autofocus ng-model="user.confirmedPassword">
                    <br>
                    <button class="btn btn-primary btn-block" type="submit">Create account</button>
                </form>
            </div>
        </div>
    </div>
</header>