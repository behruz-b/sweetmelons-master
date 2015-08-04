angular.module('globalVariables', []).run ($rootScope) ->
    $rootScope.Glob =
        Users: []
        Tests: []
        Permission: window.Permission

    window.Permission = undefined