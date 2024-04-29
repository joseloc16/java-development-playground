-- Data para generación de contrato en word
insert into contract (tipo, titulo, descripcion_contrato, clausula)
values ('c_docente','{TITULO}',
        'Por el presente documento celebran el contrato de servicio docente, ' ||
        'de una parte la DIRECCION REGIONAL DE EDUCACION, UNIDAD DE GESTION EDUCATIVA LOCAL, ' ||
        '(según sea el caso) de {INTITUCION}, con domicilio en {DOMICILIO_DIRECTOR} ' ||
        'representada para estos efectos por su Director(a), el/la Señor(a) {NOMBRE_DIRECTOR}' ||
        ' identificado con D.N.I. N° {DNI_DIRECTOR}, designado(a) mediante Resolucion N° ' ||
        '{NUMERO_RESOLUCION}. A quien en adelante se denomina LA DRE/GRE/UGEL; y de otra parte, ' ||
        'el Señor(a) {NOMBRE_DOCENTE}, identificado(a) con D.N.I. N° {DNI_DOCENTE} y domiciliado ' ||
        'en {DOMICILIO_DOCENTE},  y correo electrónico {CORREO_DOCENTE} quien en adelante se ' ||
        'denomina PROFESOR(A); en los términos y condiciones siguientes:',
        'CLAUSULA PRIMERA.- En atención a las necesidades de contar con los servicio de un profesional docente, se adjudicó la palza orgánica/eventual/temporal/horas de libre disponibilidad a don(ña) {NOMBRE_DOCENTE} para desempeñar funciones docentes.');
