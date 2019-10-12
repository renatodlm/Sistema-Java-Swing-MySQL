package Model;

/**
 *
 * @author BLSoft Desenvolvimento de Sistemas
 */
public class ModelFormaPagamento {

    private int codigo;
    private String descricao;
    private float desconto;
    private int quantidadeParcelas;
    private String observacao;
    private boolean situacao;

    /**
     * Construtor
     */
    public ModelFormaPagamento() {
    }

    /**
     * seta o valor de codigo
     *
     * @param pCodigo seta o valor de codigo
     */
    public void setCodigo(int pCodigo) {
        this.codigo = pCodigo;
    }

    /**
     * return codigo
     *
     * @return retorna o codigo da forma de pagamento
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * seta o valor de descricao
     *
     * @param pDescricao seta descrição
     */
    public void setDescricao(String pDescricao) {
        this.descricao = pDescricao;
    }

    /**
     * return descricao
     *
     * @return Retorna a descrição
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * seta o valor de desconto
     *
     * @param pDesconto seta o valor de desconto
     */
    public void setDesconto(float pDesconto) {
        this.desconto = pDesconto;
    }

    /**
     * return desconto
     *
     * @return retorna os descontos
     */
    public float getDesconto() {
        return this.desconto;
    }

    /**
     * seta o valor de quantidadeParcelas
     *
     * @param pQuantidadeParcelas seta quantidade de parcelas
     */
    public void setQuantidadeParcelas(int pQuantidadeParcelas) {
        this.quantidadeParcelas = pQuantidadeParcelas;
    }

    /**
     * return quantidadeParcelas
     *
     * @return retorna quantidade de parecelas
     */
    public int getQuantidadeParcelas() {
        return this.quantidadeParcelas;
    }

    /**
     * seta o valor de observacao
     *
     * @param pObservacao seta Observação na variavel
     */
    public void setObservacao(String pObservacao) {
        this.observacao = pObservacao;
    }

    /**
     * return observacao
     *
     * @return retorna observação
     */
    public String getObservacao() {
        return this.observacao;
    }

    /**
     * seta o valor de situacao
     *
     * @param pSituacao set o valor de sitaução
     */
    public void setSituacao(boolean pSituacao) {
        this.situacao = pSituacao;
    }

    /**
     * return situacao
     *
     * @return retorna tipo de variavel de Situação
     */
    public boolean isSituacao() {
        return this.situacao;
    }

    @Override
    public String toString() {
        return "ModelFormaPagamento {" + "::codigo = " + this.codigo + "::descricao = " + this.descricao + "::desconto = " + this.desconto + "::quantidadeParcelas = " + this.quantidadeParcelas + "::observacao = " + this.observacao + "::situacao = " + this.situacao + "}";
    }
}
