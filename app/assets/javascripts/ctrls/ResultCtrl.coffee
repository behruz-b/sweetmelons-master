angular.module('myApp.controllers')
.controller 'ResultCtrl', class
    constructor: ($scope, @$log, Users) ->
        $scope.userAnswers = []

        vm = @

        $scope.getUserAnswers = () =>
            Users.results((data) =>
                if data
                    $scope.userAnswers = data
                    @$log.info(data)
            ).$promise

        $scope.getUserAnswers()

