angular.module('myApp')
.factory('Users', ['$resource', ($resource) ->
    $resource('/users/:id', id: '@id',
            {
                'update':'method': 'PUT',
                'query': {method: 'GET', url: '/users/list', isArray: true}
                'roles': {method: 'GET', url: '/users/roles', isArray: true},
                'states': {method: 'GET', url: '/users/states', isArray: true},
                'userObj': {method: 'POST', url: '/userObj'},
                'signIn': {method: 'POST', url: '/signIn'}
                'signUp': {method: 'POST', url: '/users/signup'}
                'answ': {method: 'POST', url: '/user/answer'}
            }
    )
])

.factory('Roles', ['$resource', ($resource) ->
    $resource('/users/roles/:id', id: '@id',
        'query': {method: 'GET', isArray: yes}
    )
])

.factory('Search', ['$resource', ($resource) ->
    $resource('/search/params/:cityId', cityId: '@cityId',
      {
        'query': {method: 'GET', isArray: yes},
        'findHotel': {method: 'GET', url: '/search/params'}
      }
    )
  ])

.factory('Questions', ['$resource', ($resource) ->
    $resource('/questions/list/:id', id: '@id',
      {
        'update':'method': 'PUT',
        'query': {method: 'GET', url: '/questions/list', isArray: true}
        'tests': {method: 'GET', url: '/questions/list', isArray: true},
        'add': {method: 'POST', url: '/questions/add'}
        'save': {method: 'POST', url: '/test/save'}
      }
    )
  ])
