angular.module('myApp.controllers')
.controller 'SignInCtrl', class
    constructor: ($scope, @$log, $state, Users) ->
        vm = @

        vm.signIn = (credential) =>
            login = credential.login
            pass = credential.password
            if !login or !pass
                alert("Please enter login and password.")
                return no

            loginPass =
                login: login
                password: pass

            $log.info(loginPass)

            Users.signIn(loginPass, (data) =>
                if data
#                    questId = 0
#                    @$log.info("questId")
#                    @$log.info(questId)
#                    @$log.info("questId1")
                    $state.go('root.start', questId:0)
                else
                    alert("Incorrect login or password.")
            ).$promise