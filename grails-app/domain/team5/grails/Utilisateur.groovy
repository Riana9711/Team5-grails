package team5.grails

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email
    Date createdAt
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static hasMany = [produits: Produit]

    Set<Role> getAuthorities() {
        (UtilisateurRole.findAllByUtilisateur(this) as List<UtilisateurRole>)*.role as Set<Role>
    }

    static constraints = {
        username nullable: false, blank: false, unique: true
        email nullable: Boolean.FALSE, blank: Boolean.FALSE, unique: Boolean.TRUE
        password nullable: false, blank: false, password: true
        createdAt nullable: Boolean.TRUE
    }

    static mapping = {
	    password column: '`password`'
    }
}
