angular.module('myApp.controllers')
.controller 'SignUpCtrl', class
    constructor: ($scope, $state, Users) ->
        vm = @
        $scope.user = {}

        vm.signUp = (user) =>
            Users.signUp(user, (data) =>
                if data
                    $state.go('root.questions')
            ).$promise

#        $scope.signUp = () ->
#            login = $scope.user.login
#            pass = $scope.user.password
#            confirmPassword = $scope.user.confirmPassword
#            firstName = $scope.user.firstName
#            lastName = $scope.user.lastName
#            address = $scope.user.address
#            if !login or !pass
#                alert("Please enter login and password.")
#                return no
#
#            regUser =
#                login: login
#                password: pass
#                firstName: firstName
#                lastName: lastName
#                address: address
#
#            Users.signUp(regUser, () ->
#                window.location = "/#" + $scope.Glob.Route.Content.All
#                window.location.reload()
#            , (error) ->
#                alert("Incorrect fields.")
#            )
