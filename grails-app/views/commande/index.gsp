<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'commande.label', default: 'Commande')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-commande" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-commande" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{--<f:table collection="${commandeList}" />--}%
            <table>
                <thead>
                <tr>
                    %{--<g:each in="${domainProperties}" var="p" status="i">
                        <g:set var="propTitle">
                            ${domainClass.propertyName}.${p.name}.label
                        </g:set>
                        <g:sortableColumn property="${p.name}"
                                          title="${message(code: propTitle, default: p.naturalName)}" />
                    </g:each>--}%
                    <th>Utilisateur</th>
                    <th>Produit</th>
                    <th>Nombre</th>
                    <th>Status</th>
                    <th>Prix Total</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${commandeList}" var="command" status="i">
                    <tr>
                        <td>${command.utilisateur}</td>
                        <td><g:link controller="produit" action="show"
                                    params='[id: "${command.produit.id}"]'>${command.produit}</g:link></td>
                        <td>${command.nombre}</td>
                        <td>
                            <g:if test="${command.statut==0}">
                                En attente
                            </g:if>
                            <g:else>
                                Validé
                            </g:else>
                        </td>
                        <td>${command.prixTotal} ar</td>
                        <td><g:link controller="commande" action="show"
                                    params='[id: "${command.id}"]'>Détail</g:link>
                        <g:if test="${command.statut==0}">
                            <g:link controller="commande" action="accepterCommande"
                                    params='[id: "${command.id}"]'>Accepter</g:link>
                        </g:if>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${commandeCount ?: 0}" />
            </div>
        </div>
    </body>
</html>