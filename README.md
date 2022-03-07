# MagicLibrary

Repositorio para el código base de la asignatura [Ingeniería Software](https://www.us.es/estudiar/que-estudiar/oferta-de-grados/grado-en-ingenieria-de-las-tecnologias-de-telecomunicacion/1990039), de 3º del GITT de la Universidad de Sevilla

Está basado en el diseño del Tutorial UML que incluye [MagicDraw](https://www.3ds.com/products-services/catia/products/no-magic/magicdraw/), y que se utiliza en las prácticas de la asignatura

- Para el Modelo se utiliza la API [JPA](https://www.oracle.com/technical-resources/articles/java/jpa.html), con [eclipselink](https://www.eclipse.org/eclipselink/) como proveedor y la base de datos [H2](https://h2database.com/html/main.html) (en memoria), que habría que cambiar en el entorno de operación

- Para la Vista se utiliza [Swing](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html)

- Está organizado como un proyecto [Gradle](https://gradle.org/) e incluye el gradle wrapper, aunque para las prácticas no será obligatorio utilizarlo

