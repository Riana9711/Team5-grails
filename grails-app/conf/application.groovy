
grails.plugin.springsecurity.logout.postOnly = false
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'team5.grails.Utilisateur'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'team5.grails.UtilisateurRole'
grails.plugin.springsecurity.rest.token.storage.jwt.secret = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJtb29jX3NwcmluZ19zZWN1cml0eSIsImlhdCI6MTU5NjIwNjcxMywiZXhwIjoxNjI3NzQyNzEzLCJhdWQiOiJtb29jX3N0dWRlbnRzIiwic3ViIjoibW9vY19zcHJpbmdfc2VjdXJpdHkiLCJHaXZlbk5hbWUiOiJNb29jIn0.gx1iCqhrx1gikFigcUTqlBBdGZPbXs6bZYxDp5V93fs"
grails.plugin.springsecurity.authority.className = 'team5.grails.Role'
grails.plugin.springsecurity.requestMap.className = 'team5.grails.UtilisateurRole'
grails.plugin.springsecurity.securityConfigType = 'Annotation'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/h2-console/**', access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

