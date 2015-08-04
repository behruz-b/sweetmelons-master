class LoginCtrl

    constructor: ($scope, Users) ->
        $scope.credential = {}

        $scope.signIn = () ->
            login = $scope.credential.login
            pass = $scope.credential.password
            if !login or !pass
                alert("Please enter login and password.")
                return no

            loginPass =
                login: login
                password: pass

            Users.signIn(loginPass, () ->
                window.location = "/#" + $scope.Glob.Route.Content.All
                window.location.reload()
            , (error) ->
                alert("Incorrect login or password.")
            )

controllersModule.controller('LoginCtrl', LoginCtrl)