angular.module('myApp')
.controller 'FileUploadDestroyCtrl', class
  ($scope, $http) ->
    file = $scope.file
    state = undefined
    if file.url

      file.$state = ->
        state

      file.$destroy = ->
        state = 'pending'
        $http(
          url: file.deleteUrl
          method: file.deleteType).then (->
            state = 'resolved'
            $scope.clear file
          ), ->
        state = 'rejected'

    else if !file.$cancel and !file._index
        file.$cancel = ->
        $scope.clear file