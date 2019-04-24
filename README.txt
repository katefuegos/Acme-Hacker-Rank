1. Hemos decidido crear una entidad "Notification" para poder avisar de las brechas de seguridad.

2. En los tests de rendimiento, se llegan a las cifras indicadas en el documento aunque se pueda llegar a más usuarios aumentando el tiempo de espera de las peticiones POST.
Pensamos que sería mas realista aumentar dicho tiempo de espera en esas circunstancias debido a que se tarda más de 1.5 segundos en rellenar el formulario.
Incluso así hemos decidido seguir las indicaciones del documento de la asignatura y dejarlo en 1.5 segundos de tiempo de espera para todas las peticiones ya sean de tipo GET o POST.
