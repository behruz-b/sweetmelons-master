angular.module('myApp.controllers')
.controller 'UserCtrl', class
    constructor: ($scope, @$log, $route, Users) ->
        glob = $scope.Glob
        $scope.users = []

        $scope.getUsers = () =>
            Users.query((data) =>
                if data
                    $scope.users = data
                    @$log.info($scope.users)
            ).$promise

        $scope.getUsers()

