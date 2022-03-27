package team5.grails

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class UtilisateurRole implements Serializable {

	private static final long serialVersionUID = 1

	Utilisateur utilisateur
	Role role

	@Override
	boolean equals(other) {
		if (other instanceof UtilisateurRole) {
			other.utilisateurId == utilisateur?.id && other.roleId == role?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (utilisateur) {
            hashCode = HashCodeHelper.updateHash(hashCode, utilisateur.id)
		}
		if (role) {
		    hashCode = HashCodeHelper.updateHash(hashCode, role.id)
		}
		hashCode
	}

	static UtilisateurRole get(long utilisateurId, long roleId) {
		criteriaFor(utilisateurId, roleId).get()
	}

	static boolean exists(long utilisateurId, long roleId) {
		criteriaFor(utilisateurId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long utilisateurId, long roleId) {
		UtilisateurRole.where {
			utilisateur == Utilisateur.load(utilisateurId) &&
			role == Role.load(roleId)
		}
	}

	static UtilisateurRole create(Utilisateur utilisateur, Role role, boolean flush = false) {
		def instance = new UtilisateurRole(utilisateur: utilisateur, role: role)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(Utilisateur u, Role r) {
		if (u != null && r != null) {
			UtilisateurRole.where { utilisateur == u && role == r }.deleteAll()
		}
	}

	static int removeAll(Utilisateur u) {
		u == null ? 0 : UtilisateurRole.where { utilisateur == u }.deleteAll() as int
	}

	static int removeAll(Role r) {
		r == null ? 0 : UtilisateurRole.where { role == r }.deleteAll() as int
	}

	static constraints = {
	    utilisateur nullable: false
		role nullable: false, validator: { Role r, UtilisateurRole ur ->
			if (ur.utilisateur?.id) {
				if (UtilisateurRole.exists(ur.utilisateur.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['utilisateur', 'role']
		version false
	}
}
