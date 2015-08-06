dependencies = [
    'globalVariables',
    'ngGrid',
    'ui.router',
    'ui.bootstrap',
    'myApp.filters',
    'myApp.controllers',
    'myApp.directives',
    'myApp.common',
    'ngResource',
    'ui.sortable',
    'angular-growl',
    'ngAnimate',
    'mgcrea.ngStrap',
    'blueimp.fileupload',
    'LocalForageModule'
]

app = angular.module('myApp', dependencies)
app.config([
    '$httpProvider'
    'fileUploadProvider'
    ($httpProvider, fileUploadProvider) ->
        delete $httpProvider.defaults.headers.common['X-Requested-With']
        fileUploadProvider.defaults.redirect = window.location.href.replace(/\/[^\/]*$/, '/cors/result.html?%s')
        # Demo settings:
        angular.extend fileUploadProvider.defaults,
            disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent)
            maxFileSize: 5000000
            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
])
app.config ($datepickerProvider) ->
    angular.extend $datepickerProvider.defaults,
        dateFormat: 'dd/MM/yyyy'
        startWeek: 1

@commonModule = angular.module('myApp.common', [])
angular.module('myApp.controllers', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])




