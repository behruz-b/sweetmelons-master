class SignUpCtrl

    constructor: ($scope, Users) ->
        $scope.user = {}

        $scope.signUp = () ->
            login = $scope.user.login
            pass = $scope.user.password
            confirmPassword = $scope.user.confirmPassword
            firstName = $scope.user.firstName
            lastName = $scope.user.lastName
            address = $scope.user.address
            if !login or !pass
                alert("Please enter login and password.")
                return no

            regUser =
                login: login
                password: pass
                firstName: firstName
                lastName: lastName
                address: address

            Users.signUp(regUser, () ->
                window.location = "/#" + $scope.Glob.Route.Content.All
                window.location.reload()
            , (error) ->
                alert("Incorrect fields.")
            )

controllersModule.controller('SingUpCtrl', SignUpCtrl)