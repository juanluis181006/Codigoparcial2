import java.util.Scanner;

public class TiendaRopa {
    String producto;
    int cantidad;
    double precio;
    double subtotal;
    double iva;
    double descuento;
    double total;

    public enum TipoProduc {
        CAMISETA(25000), PANTALON(45000), CHAQUETA(65000);

        private final double precio;

        TipoProduc(double precio) {
            this.precio = precio;
        }

        public double getPrecio() {
            return precio;
        }
    }

    TipoProduc tipoProducto;

    public TiendaRopa(String producto, int cantidad, double precio, TipoProduc tipoProducto) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
    }

    public double calcularSubtotal() {
        subtotal = precio * cantidad;
        return subtotal;
    }

    public double calcularDescuento() {
        if (subtotal == 0) calcularSubtotal();
        if (cantidad >= 3 && cantidad <= 4) {
            descuento = subtotal * 0.10;
        } else if (cantidad > 4) {
            descuento = subtotal * 0.15;
        } else {
            descuento = 0;
        }
        return descuento;
    }

    public double calcularIva() {
        if (subtotal == 0) calcularSubtotal();
        iva = subtotal * 0.19;
        return iva;
    }

    public double calcularTotal() {
        calcularSubtotal();
        calcularDescuento();
        calcularIva();
        total = subtotal + iva - descuento;
        return total;
    }

    public void mostrarDetalle() {
        calcularTotal();
        System.out.println("Producto: " + producto);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Valor unitario: $" + precio);
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("IVA: $" + iva);
        System.out.println("Descuento: $" + descuento);
        System.out.println("Total: $" + total);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la cantidad de CAMISETAS que desea comprar:");
        int cantCamisetas = sc.nextInt();

        System.out.println("Ingrese la cantidad de PANTALONES que desea comprar:");
        int cantPantalones = sc.nextInt();

        System.out.println("Ingrese la cantidad de CHAQUETAS que desea comprar:");
        int cantChaquetas = sc.nextInt();

        TiendaRopa compraCamiseta = new TiendaRopa("Camiseta", cantCamisetas, TipoProduc.CAMISETA.getPrecio(), TipoProduc.CAMISETA);
        TiendaRopa compraPantalon = new TiendaRopa("Pantalón", cantPantalones, TipoProduc.PANTALON.getPrecio(), TipoProduc.PANTALON);
        TiendaRopa compraChaqueta = new TiendaRopa("Chaqueta", cantChaquetas, TipoProduc.CHAQUETA.getPrecio(), TipoProduc.CHAQUETA);

        System.out.println("\n------ DETALLE DE COMPRA ------");
        compraCamiseta.mostrarDetalle();
        compraPantalon.mostrarDetalle();
        compraChaqueta.mostrarDetalle();

        double subtotalGeneral = compraCamiseta.subtotal + compraPantalon.subtotal + compraChaqueta.subtotal;
        double descuentoGeneral = compraCamiseta.descuento + compraPantalon.descuento + compraChaqueta.descuento;
        double ivaGeneral = compraCamiseta.iva + compraPantalon.iva + compraChaqueta.iva;
        double totalGeneral = compraCamiseta.total + compraPantalon.total + compraChaqueta.total;

        System.out.println(" Su recivo es el siguiente:");
        System.out.println("Subtotal general: $" + subtotalGeneral);
        System.out.println("Descuento total:  $" + descuentoGeneral);
        System.out.println("IVA total:        $" + ivaGeneral);
        System.out.println("TOTAL A PAGAR:    $" + totalGeneral);

        sc.close();
    }
}