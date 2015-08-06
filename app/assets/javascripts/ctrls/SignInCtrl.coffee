angular.module('myApp.controllers')
.controller 'SignInCtrl', class
    constructor: ($scope, @$log, $state, $localForage, Users) ->
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

            @$log.info(loginPass)

            Users.signIn(loginPass, (data) =>
                if data
                    @$log.info("data1")
                    @$log.info(JSON.stringify(data))
                    @$log.info(data)
                    @$log.info("data2")
                    $scope.saveUserInStorage(loginPass)
                    $state.go('root.start', questId:0)
                else
                    alert("Incorrect login or password.")
            ).$promise

        $scope.saveUserInStorage = (loginPass) =>
            Users.userObj(loginPass, (data) =>
                if data
                    $localForage.setItem('userm', data)
            ).$promise