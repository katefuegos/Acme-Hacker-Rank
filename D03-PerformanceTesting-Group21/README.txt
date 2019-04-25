1. Hemos decidido crear una entidad "Notification" para poder avisar de las brechas de seguridad.

2. En los tests de rendimiento, se llegan a las cifras indicadas en el documento aunque se pueda llegar a más usuarios aumentando 
el tiempo de espera de las peticiones POST.
Pensamos que sería mas realista aumentar dicho tiempo de espera en esas circunstancias debido a que se tarda más de 1.5 segundos 
en rellenar el formulario.
Incluso así hemos decidido seguir las indicaciones del documento de la asignatura y dejarlo en 1.5 segundos de tiempo de espera 
para todas las peticiones ya sean de tipo GET o POST.

3. Respecto a los cuellos de botella por parte del hardware en los tests de rendimiento, hemos dado por hecho que el máximo cuello 
de botella es la tarjeta de red. Por ello en el documento se habla del siguiente cuello de botella que es la CPU.

4. Se han dividido los tests de rendimiento en 3 documentos, cada uno representa al equipo de hardware usado para los tests, en 
cada uno de ellos se puede ver las especificaciones, tanto del equipo físico como virtual.